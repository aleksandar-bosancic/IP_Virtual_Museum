<%@ page import="com.museum.admin.application.beans.MuseumBean" %>
<%@ page import="java.util.List" %>
<%@ page import="com.museum.admin.application.beans.MediaBean" %>
<%@ page import="java.util.Optional" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:useBean id="museumBean" class="com.museum.admin.application.beans.MuseumBean"/>
<jsp:useBean id="museumService" class="com.museum.admin.application.services.MuseumService"/>
<jsp:useBean id="mediaService" class="com.museum.admin.application.services.MediaService"/>
<% List<MediaBean> mediaList = mediaService.findAllByMuseumId(Integer.parseInt(request.getParameter("id")));
%>
<html>
<head>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>

    <title>Preview Presentation</title>
</head>
<body style="background-color: #e3f2fd">
    <div class="container-fluid pe-3 ps-3" style="display: flex; flex-direction: column; justify-content: space-around; align-items: center">
        <div class="mt-3 mb-3"><H1>Presentation preview</H1></div>
        <% Optional<MediaBean> videoMedia = mediaList.stream().filter(MediaBean::isVideo).findFirst();
            if (videoMedia.isPresent()) { %>
                <div class="embed-responsive embed-responsive-16by9">
                    <% if(videoMedia.get().getPath().contains("https")) { %>
                    <iframe width="640"  height="420" src="<%=videoMedia.get().getPath()%>">
                    </iframe>
                    <% } else { %>
                    <video id="video" src="<%=videoMedia.get().getPath()%>" class="embed-responsive-item" controls>
                    </video>
                    <% } %>
                </div>
        <% } %>
        <% for (MediaBean media : mediaList) {
            if (!media.isVideo()) {%>
        <div class="mt-3">
            <img src="<%=media.getPath()%>" class="img-fluid" alt="Responsive image">
        </div>
        <% } }%>
        <div class="mt-5 mb-5">
            <a class="btn btn-primary" href="../index.jsp">Exit preview</a>
        </div>
    </div>
</body>
</html>
