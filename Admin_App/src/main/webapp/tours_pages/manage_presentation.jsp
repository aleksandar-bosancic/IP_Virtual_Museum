<%@ page import="com.museum.admin.Admin_App.beans.MuseumBean" %>
<%@ page import="java.util.List" %>
<%@ page import="com.museum.admin.Admin_App.beans.MediaBean" %>
<%@ page import="java.util.ArrayList" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.Optional" %>
<jsp:useBean id="museumService" class="com.museum.admin.Admin_App.services.MuseumService"/>
<jsp:useBean id="museumBean" class="com.museum.admin.Admin_App.beans.MuseumBean"/>
<jsp:useBean id="mediaService" class="com.museum.admin.Admin_App.services.MediaService"/>
<!DOCTYPE html>
<% List<MuseumBean> museumList = museumService.findAll();
    List<MediaBean> mediaList = new ArrayList<>();
    if(request.getParameter("id") != null) {
        mediaList = mediaService.findAllByMuseumId(Integer.parseInt(request.getParameter("id")));
    }
%>
<html>
<head>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>

    <title>Manage Presentations</title>
</head>
<body onload="setHiddenInput()" style="background-color: #e3f2fd">
<script type="text/javascript">
    function museumSelectChanged() {
        let museum = document.getElementById("museumId").value;
        const numberRegex = /\?id=[0-9]+/
        if(window.location.href.match(numberRegex)){
            let url = window.location.toString();
            window.location.href = url.replace(numberRegex, '?id=' + museum);
        } else {
            window.location.href = document.URL + "?id=" + museum;
        }
    }
    function setHiddenInput() {
        document.getElementById('museumIdInput').value = document.getElementById('museumId').value;
    }
</script>
<script type="text/javascript">
    $(document).ready(
        function(){
            $('#fileSubmit').attr('disabled',true);
            $('#fileUpload').change(
                function(){
                    if ($(this).val()){
                        $('#fileSubmit').removeAttr('disabled');
                    }
                    else {
                        $('#fileSubmit').attr('disabled',true);
                    }
                });
        });
</script>
<div class="container-fluid mt-5 pe-3 ps-3" style="display: flex; flex-direction: column; justify-content: space-around; align-items: center">
    <div class="mb-5" style="display: flex; width: 90vw; flex-direction: row; justify-content: space-evenly">
        <div class="mb-3">
            <select class="form-select" id="museumId" name="museumId" onchange="museumSelectChanged()">
                <option selected disabled>Select museum</option>
                <% for (MuseumBean museum : museumList) {
                    if (request.getParameter("id") != null
                    && Integer.parseInt(request.getParameter("id")) == museum.getId()) { %>
                    <option value="<%=museum.getId()%>" selected><%= museum.getName() %></option>
                    <% } else { %>
                <option value="<%=museum.getId()%>"><%= museum.getName() %></option>
                <% } }%>
            </select>
        </div>
        <% if (mediaList.size() < 11) {%>
        <div class="mb-3">
            <form style="display: flex; flex-direction: row" method="post" action="UploadServlet" enctype="multipart/form-data">
                <input type="number" name="museumIdInput" id="museumIdInput" hidden>
                <input class="form-control" type="file" id="fileUpload" name="UploadServlet" accept="image/*,video/*">
                <input class="btn btn-primary" name="UploadServlet" id="fileSubmit" type="submit" value="Add">
            </form>
        </div>
        <% } %>
    </div>
    <div class="mb-5" style="display: flex; width: 90vw; flex-direction: column; justify-content: space-around; align-items: center">
        <div class="mb-3" style="width: 100%">
            <table class="table align-middle" id="mediaTable">
                <thead>
                <tr>
                    <th scope="col">Media</th>
                    <th scope="col">Edit</th>
                    <th scope="col">Remove</th>
                </tr>
                </thead>
                <tbody>
                <% Optional<MediaBean> videoMedia = mediaList.stream().filter(MediaBean::isVideo).findFirst();
                    if (videoMedia.isPresent()) { %>
                <tr>
                    <td>
                        <div style="display: flex; flex-direction: row; justify-content:  space-between; align-items: center">
                            <div class="embed-responsive embed-responsive-16by9">
                                <video id="video" src="<%=videoMedia.get().getPath()%>" class="embed-responsive-item" controls>
                                </video>
                            </div>
                        </div>
                    </td>
                    <td>
                        <a class="btn btn-primary" type="submit">
                            <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-pencil-square" viewBox="0 0 16 16">
                                <path d="M15.502 1.94a.5.5 0 0 1 0 .706L14.459 3.69l-2-2L13.502.646a.5.5 0 0 1 .707 0l1.293 1.293zm-1.75 2.456-2-2L4.939 9.21a.5.5 0 0 0-.121.196l-.805 2.414a.25.25 0 0 0 .316.316l2.414-.805a.5.5 0 0 0 .196-.12l6.813-6.814z"></path>
                                <path fill-rule="evenodd" d="M1 13.5A1.5 1.5 0 0 0 2.5 15h11a1.5 1.5 0 0 0 1.5-1.5v-6a.5.5 0 0 0-1 0v6a.5.5 0 0 1-.5.5h-11a.5.5 0 0 1-.5-.5v-11a.5.5 0 0 1 .5-.5H9a.5.5 0 0 0 0-1H2.5A1.5 1.5 0 0 0 1 2.5v11z"></path>
                            </svg>
                            Edit
                        </a>
                    </td>
                    <td>
                        <a class="btn btn-primary" href="delete_media.jsp?id=<%=videoMedia.get().getId()%>&museumId=<%=videoMedia.get().getMuseumId()%>">
                            <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-trash-fill" viewBox="0 0 16 16">
                                <path d="M2.5 1a1 1 0 0 0-1 1v1a1 1 0 0 0 1 1H3v9a2 2 0 0 0 2 2h6a2 2 0 0 0 2-2V4h.5a1 1 0 0 0 1-1V2a1 1 0 0 0-1-1H10a1 1 0 0 0-1-1H7a1 1 0 0 0-1 1H2.5zm3 4a.5.5 0 0 1 .5.5v7a.5.5 0 0 1-1 0v-7a.5.5 0 0 1 .5-.5zM8 5a.5.5 0 0 1 .5.5v7a.5.5 0 0 1-1 0v-7A.5.5 0 0 1 8 5zm3 .5v7a.5.5 0 0 1-1 0v-7a.5.5 0 0 1 1 0z"></path>
                            </svg>
                            Remove
                        </a>
                    </td>
                </tr>
                <% } %>
                <% for (MediaBean media : mediaList) {
                    if (!media.isVideo()) {%>
                <tr>
                    <td>
                        <div style="display: flex; width: 100vh; flex-direction: row; justify-content:  space-between; align-items: center">
                            <div>
                                <div>
                                    <img src="<%=media.getPath()%>" class="img-fluid" alt="Responsive image">
                                </div>
                            </div>
                        </div>
                    </td>
                    <td>
                        <button class="btn btn-primary" type="submit" name="submit">
                            <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-pencil-square" viewBox="0 0 16 16">
                                <path d="M15.502 1.94a.5.5 0 0 1 0 .706L14.459 3.69l-2-2L13.502.646a.5.5 0 0 1 .707 0l1.293 1.293zm-1.75 2.456-2-2L4.939 9.21a.5.5 0 0 0-.121.196l-.805 2.414a.25.25 0 0 0 .316.316l2.414-.805a.5.5 0 0 0 .196-.12l6.813-6.814z"></path>
                                <path fill-rule="evenodd" d="M1 13.5A1.5 1.5 0 0 0 2.5 15h11a1.5 1.5 0 0 0 1.5-1.5v-6a.5.5 0 0 0-1 0v6a.5.5 0 0 1-.5.5h-11a.5.5 0 0 1-.5-.5v-11a.5.5 0 0 1 .5-.5H9a.5.5 0 0 0 0-1H2.5A1.5 1.5 0 0 0 1 2.5v11z"></path>
                            </svg>
                            Edit
                        </button>
                    </td>
                    <td>
                        <a class="btn btn-primary" href="delete_media.jsp?id=<%=media.getId()%>&museumId=<%=media.getMuseumId()%>">
                            <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-trash-fill" viewBox="0 0 16 16">
                                <path d="M2.5 1a1 1 0 0 0-1 1v1a1 1 0 0 0 1 1H3v9a2 2 0 0 0 2 2h6a2 2 0 0 0 2-2V4h.5a1 1 0 0 0 1-1V2a1 1 0 0 0-1-1H10a1 1 0 0 0-1-1H7a1 1 0 0 0-1 1H2.5zm3 4a.5.5 0 0 1 .5.5v7a.5.5 0 0 1-1 0v-7a.5.5 0 0 1 .5-.5zM8 5a.5.5 0 0 1 .5.5v7a.5.5 0 0 1-1 0v-7A.5.5 0 0 1 8 5zm3 .5v7a.5.5 0 0 1-1 0v-7a.5.5 0 0 1 1 0z"></path>
                            </svg>
                            Remove
                        </a>
                    </td>
                </tr>
                <% } }%>
                </tbody>
            </table>
        </div>
    </div>
    <div class="mb-5">
        <a class="btn btn-primary" href="../index.jsp">Save changes</a>
    </div>
</div>
</body>
</html>
