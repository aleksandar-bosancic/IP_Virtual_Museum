package com.museum.user.backend.services;

import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

@Service
public class MailService {
    private static final String TICKET_PDF = "Ticket.pdf";
    private final JavaMailSender emailSender;

    public MailService(JavaMailSender emailSender) {
        this.emailSender = emailSender;
    }
    public void sendTicketMail(String path, String email) throws MessagingException {
        MimeMessage message = emailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);
        helper.setFrom("ip-virtual-museum@gmail.com");
        helper.setTo(email);
        helper.setSubject("Ticket");
        helper.setText("Ticket for virtual tour is in the attachment.");
        FileSystemResource file
                = new FileSystemResource(new File(path));
        helper.addAttachment(TICKET_PDF, file, "application/pdf");
        emailSender.send(message);
        try {
            Files.delete(new File(path).toPath());
        } catch (IOException exception) {
            System.err.println(exception.getMessage());
        }
    }

    public void sendReminderMail(String email, String message) {
        SimpleMailMessage mail = new SimpleMailMessage();
        mail.setFrom("ip-virtual-museum@gmail.com");
        mail.setTo(email);
        mail.setSubject("Tour reminder");
        mail.setText(message);
        emailSender.send(mail);
    }
}
