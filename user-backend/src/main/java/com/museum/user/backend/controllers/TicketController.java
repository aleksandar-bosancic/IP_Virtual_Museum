package com.museum.user.backend.controllers;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfWriter;
import com.museum.user.backend.model.Ticket;
import com.museum.user.backend.model.TicketRequest;
import com.museum.user.backend.model.entities.TicketEntity;
import com.museum.user.backend.model.entities.TourEntity;
import com.museum.user.backend.model.entities.UserEntity;
import com.museum.user.backend.repositories.TicketEntityRepository;
import com.museum.user.backend.repositories.TourEntityRepository;
import com.museum.user.backend.repositories.UserEntityRepository;
import com.museum.user.backend.services.MailService;
import com.museum.user.backend.services.ScheduleService;
import com.museum.user.backend.util.Util;
import org.modelmapper.ModelMapper;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

import javax.mail.MessagingException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.Instant;

@RestController
public class TicketController {
    private static final String START_REMINDER_TEXT = "Your tour starts in 1 hour.";
    private static final String END_REMINDER_TEXT = "Your tour ends in 5 minutes.";
    public static final String TICKET_PDF = "Ticket.pdf";
    private final UserEntityRepository userRepository;
    private final TourEntityRepository tourRepository;
    private final TicketEntityRepository ticketRepository;
    private final ScheduleService scheduleService;
    private final MailService mailService;
    private final RestTemplate restTemplate;
    private final ModelMapper modelMapper;
    String verifyResponse;

    public TicketController(UserEntityRepository userRepository, TourEntityRepository tourRepository,
                            TicketEntityRepository ticketRepository, ScheduleService scheduleService,
                            MailService mailService, RestTemplateBuilder restTemplateBuilder,
                            ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.tourRepository = tourRepository;
        this.ticketRepository = ticketRepository;
        this.scheduleService = scheduleService;
        this.mailService = mailService;
        this.restTemplate = restTemplateBuilder.build();
        this.modelMapper = modelMapper;
    }

    @PostMapping("/generate-ticket")
    public Ticket generateTicket(@RequestBody TicketRequest ticketRequest) throws DocumentException, IOException, MessagingException {
        if (ticketRequest.getUsername() == null || ticketRequest.getUsername().equals("")
                || ticketRequest.getTourId() == 0 || ticketRequest.getTransactionId() == 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        UserEntity user = userRepository.findByUsername(ticketRequest.getUsername());
        TourEntity tour = tourRepository.findById(ticketRequest.getTourId()).orElse(null);
        if (user == null || tour == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        String ticketNumber = Util.generateTicketNumber();
        try {
            verifyResponse = restTemplate.getForObject("http://localhost:8000/verify/" + ticketRequest.getTransactionId(), String.class);
        } catch (HttpClientErrorException exception) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        Ticket ticket;
        TicketEntity ticketEntity = new TicketEntity();
        ticketEntity.setId(ticketNumber);
        ticketEntity.setTour(tour);
        ticketEntity.setUser(user);
        ticket = modelMapper.map(ticketRepository.saveAndFlush(ticketEntity), Ticket.class);
        generateTicketPdf(ticket);
        mailService.sendTicketMail(TICKET_PDF, user.getEMail());
        Instant hourAgo = Timestamp.valueOf(ticket.getTourDate()).toInstant();
        Instant startTime = hourAgo.minus(Duration.ofHours(1));
        Instant beforeEnd = Timestamp.valueOf(ticket.getTourDate()).toInstant()
                .plus(Duration.ofMinutes(ticket.getTourDuration().longValue() * 60)).minus(Duration.ofMinutes(5));
        scheduleService.sendNotification(user.getEMail(), startTime, START_REMINDER_TEXT);
        scheduleService.sendNotification(user.getEMail(), beforeEnd, END_REMINDER_TEXT);
        return ticket;
    }

    public void generateTicketPdf(Ticket ticket) throws DocumentException, IOException {
        Rectangle pageSize = new Rectangle(500, 250);
        pageSize.setBackgroundColor(new BaseColor(0x73, 0xcb, 0xf2, 0xcc));
        Document document = new Document(pageSize);
        PdfWriter.getInstance(document, new FileOutputStream(TICKET_PDF));
        Timestamp timestamp = Timestamp.valueOf(ticket.getTourDate());
        String date = new SimpleDateFormat("EEE, dd. MMMMM, yyyy.").format(timestamp);
        String time = new SimpleDateFormat("HH:mm").format(timestamp);
        document.open();
        Font headerFont = FontFactory.getFont(FontFactory.COURIER_BOLD, 18, BaseColor.BLACK);
        Font contentFont = FontFactory.getFont(FontFactory.COURIER, 14, BaseColor.BLACK);
        Paragraph header = new Paragraph("IP Museum Ticket", headerFont);
        Paragraph ticketNumber = new Paragraph("Ticket ID: " + ticket.getId(), contentFont);
        Paragraph username = new Paragraph("User: " + ticket.getUserUsername(), contentFont);
        Paragraph museumName = new Paragraph("Museum: " + ticket.getTourMuseumName(), contentFont);
        Paragraph tourDate = new Paragraph("Date: " + date, contentFont);
        Paragraph tourDuration = new Paragraph("Starting time: " + time + ", Duration: " + ticket.getTourDuration() + "h", contentFont);
        Paragraph tourPrice = new Paragraph("Ticket price: " + ticket.getTourPrice() + "€", contentFont);
        header.setAlignment(Element.ALIGN_CENTER);
        ticketNumber.setAlignment(Element.ALIGN_LEFT);
        username.setAlignment(Element.ALIGN_LEFT);
        tourDate.setAlignment(Element.ALIGN_LEFT);
        tourDuration.setAlignment(Element.ALIGN_LEFT);
        tourPrice.setAlignment(Element.ALIGN_LEFT);
        document.add(header);
        document.add(ticketNumber);
        document.add(username);
        document.add(museumName);
        document.add(tourDate);
        document.add(tourDuration);
        document.add(tourPrice);
        document.close();
    }
}
