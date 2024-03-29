<%@ page import="java.util.regex.Pattern" %>
<%@ page import="java.util.regex.Matcher" %>
<%@ page import="jakarta.servlet.http.HttpServletResponse" %>
<jsp:useBean id="mediaService" class="com.museum.admin.application.services.MediaService"/>
<jsp:useBean id="mediaBean" class="com.museum.admin.application.beans.MediaBean"/>
<%
    int museumId = Integer.parseInt(request.getParameter("museumIdInput"));
    String path = request.getParameter("pathInput");
    if (!path.contains("youtube")) {
        response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        response.sendRedirect("manage_presentation.jsp?id=" + museumId);
    }
    String youtubePath = "https://www.youtube.com/embed/";
    Pattern pattern = Pattern.compile("[^?v=]*$");
    Matcher matcher = pattern.matcher(path);
    if (matcher.find()){
        path = youtubePath + matcher.group();
    }
    response.getWriter().println(path);
    mediaBean.setMuseumId(museumId);
    mediaBean.setVideo(true);
    mediaBean.setUrl(path);
    mediaService.insert(mediaBean);
    response.sendRedirect("manage_presentation.jsp?id=" + museumId);
%>