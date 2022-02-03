package com.museum.admin.application.servlet;

import com.museum.admin.application.beans.MediaBean;
import com.museum.admin.application.services.MediaService;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

@WebServlet(name = "UploadServlet", value = "/tours_pages/UploadServlet")
@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 1024 * 1024 * 5, maxRequestSize = 1024 * 1024 * 5 * 5)
public class UploadServlet extends HttpServlet {
    private static final long serialVersionUID = -352012349533206295L;

    private static final String UPLOAD_DIRECTORY = "images";
    private static final String DEFAULT_PATH = ".." + File.separator + "images" + File.separator;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String uploadPath = getServletContext().getRealPath("") + File.separator + UPLOAD_DIRECTORY;
        MediaService mediaService = new MediaService();
        MediaBean mediaBean = new MediaBean();
        int museumId = Integer.parseInt(request.getParameter("museumIdInput"));
        File uploadDir = new File(uploadPath);
        if (!uploadDir.exists())
            uploadDir.mkdir();
        try {
            String fileName = "";
            for (Part part : request.getParts()) {
                fileName = part.getSubmittedFileName();
                if (fileName != null) {
                    part.write(uploadPath + File.separator + fileName);
                    mediaBean.setMuseumId(museumId);
                    mediaBean.setPath(DEFAULT_PATH + fileName);
                    mediaBean.setVideo(fileName.endsWith(".mp4"));
                    mediaService.insert(mediaBean);
                }
            }
            request.setAttribute("message", "File " + fileName + " has uploaded successfully!");
        } catch (FileNotFoundException fne) {
            request.setAttribute("message", "There was an error: " + fne.getMessage());
        }
        response.sendRedirect(request.getContextPath() + "/tours_pages/manage_presentation.jsp?id=" + museumId);
    }
}
