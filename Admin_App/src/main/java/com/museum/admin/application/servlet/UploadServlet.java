package com.museum.admin.application.servlet;

import com.museum.admin.application.beans.MediaBean;
import com.museum.admin.application.services.MediaService;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;

@WebServlet(name = "UploadServlet", value = "/tours_pages/UploadServlet")
@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 1024 * 1024 * 5, maxRequestSize = 1024 * 1024 * 5 * 5)
public class UploadServlet extends HttpServlet {
    private static final long serialVersionUID = -352012349533206295L;

    private static final String VIDEO = ".mp4";
    private static final String IMAGE = ".jpg";
    public static final String MEDIA = "media";
    public static final String IMAGE_CONTENT_TYPE = "image/jpg";
    public static final String VIDEO_CONTENT_TYPE = "video/mp4";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException{
        response.setContentType(IMAGE_CONTENT_TYPE);
        MediaBean mediaBean = new MediaService().findAll().stream()
                .filter(m -> m.getId() == Integer.parseInt(request.getParameter("id")))
                .findFirst().orElse(null);
        if (mediaBean == null){
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }
        response.setContentType((mediaBean.isVideo()? VIDEO_CONTENT_TYPE : IMAGE_CONTENT_TYPE));
        File file = new File(mediaBean.getPath());
        Files.copy(file.toPath(), response.getOutputStream());
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int museumId = Integer.parseInt(request.getParameter("museumIdInput"));
        String extension;
        String uploadPath = getServletContext().getRealPath(MEDIA + File.separator + museumId);
        MediaService mediaService = new MediaService();
        MediaBean mediaBean = new MediaBean();
        File uploadDir = new File(uploadPath);
        if (!uploadDir.exists()) {
            uploadDir.mkdir();
        }
        try {
            String fileName = "";
            for (Part part : request.getParts()) {
                fileName = part.getSubmittedFileName();
                if (fileName != null) {
                    mediaBean.setMuseumId(museumId);
                    mediaBean.setVideo(fileName.endsWith(".mp4"));
                    int mediaId = mediaService.insert(mediaBean);
                    extension = (mediaBean.isVideo())? VIDEO : IMAGE;
                    mediaService.updateMediaPathURL(mediaId, "http://localhost:8080/Admin_App_war_exploded/tours_pages/UploadServlet?id="
                            + mediaId, uploadPath + File.separator + mediaId + extension);
                    part.write(uploadPath + File.separator + mediaId + extension);
                }
            }
            request.setAttribute("message", "File " + fileName + " has uploaded successfully!");
        } catch (FileNotFoundException fne) {
            request.setAttribute("message", "There was an error: " + fne.getMessage());
        }
        response.sendRedirect(request.getContextPath() + "/tours_pages/manage_presentation.jsp?id=" + museumId);
    }
}
