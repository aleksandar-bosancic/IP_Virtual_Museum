package com.museum.admin.application.servlet;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.museum.admin.application.beans.LogsBean;
import com.museum.admin.application.services.LogsService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.*;
import java.util.*;
import java.util.List;

@WebServlet(name = "DownloadPdfServlet", value = "/download-pdf-servlet")
public class DownloadPdfServlet extends HttpServlet {
    private static final long serialVersionUID = 289324814055283003L;
    private static final String LOGS_PDF = "logs.pdf";
    private final LogsService logsService = new LogsService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            generateTicketPdf();
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        response.setContentType("application/pdf");
        response.setHeader("Content-disposition", "attachment; filename=" + LOGS_PDF);
        File file = new File(LOGS_PDF);
        OutputStream out = response.getOutputStream();
        FileInputStream in = new FileInputStream(file);
        byte[] buffer = new byte[4096];
        int length;
        while ((length = in.read(buffer)) > 0) {
            out.write(buffer, 0, length);
        }
        in.close();
        out.flush();
    }

    public void generateTicketPdf() throws DocumentException, IOException {
        List<LogsBean> list = logsService.findAll();
        Document document = new Document();
        PdfWriter.getInstance(document, new FileOutputStream(LOGS_PDF));
        document.open();
        PdfPTable pdfPTable = new PdfPTable(4);
        list.forEach(log -> {
            PdfPCell usernameCell = new PdfPCell(new Paragraph(log.getUsername()));
            PdfPCell categoryCell = new PdfPCell(new Paragraph(log.getCategory()));
            PdfPCell labelCell = new PdfPCell(new Paragraph(log.getLabel()));
            PdfPCell timeStampCell = new PdfPCell(new Paragraph(String.valueOf(log.getTimestamp())));
            pdfPTable.addCell(usernameCell);
            pdfPTable.addCell(categoryCell);
            pdfPTable.addCell(labelCell);
            pdfPTable.addCell(timeStampCell);
        });
        document.add(pdfPTable);
        document.close();
    }
}
