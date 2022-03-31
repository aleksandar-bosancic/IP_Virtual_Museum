<%@ page import="java.util.stream.Collectors" %>
<%@ page import="com.museum.admin.application.beans.UserStatisticBean" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<jsp:useBean id="statisticService" class="com.museum.admin.application.services.UserStatisticService"/>
<!DOCTYPE html>
<html>
<head>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/3.5.1/chart.min.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>


    <title>Museum Administrator</title>
</head>
<body onload="drawChart()">
    <nav class="navbar navbar-light" style="background-color: #e3f2fd;">
        <div class="container-fluid">
            <h1 class="navbar-brand">Museum Administration</h1>
            <ul class="nav nav-pills nav-fill" id="myTab" role="tablist">
                <li class="nav-item" role="presentation">
                    <button class="nav-link active" id="home-tab" data-bs-toggle="tab" data-bs-target="#home_panel" type="button" role="tab" aria-controls="home_panel" aria-selected="true">Home</button>
                </li>
                <li class="nav-item" role="presentation">
                    <button class="nav-link" id="museums-tab" data-bs-toggle="tab" data-bs-target="#museums_panel" type="button" role="tab" aria-controls="museums_panel" aria-selected="false">Museums</button>
                </li>
                <li class="nav-item" role="presentation">
                    <button class="nav-link" id="tours-tab" data-bs-toggle="tab" data-bs-target="#tours_panel" type="button" role="tab" aria-controls="tours_panel" aria-selected="false">Tours</button>
                </li>
                <li class="nav-item" role="presentation">
                    <button class="nav-link" id="users-tab" data-bs-toggle="tab" data-bs-target="#users_panel" type="button" role="tab" aria-controls="users_panel" aria-selected="false">Users</button>
                </li>
                <li class="nav-item" role="presentation">
                    <button class="nav-link" id="logs-tab" data-bs-toggle="tab" data-bs-target="#logs_panel" type="button" role="tab" aria-controls="logs_panel" aria-selected="false">Logs</button>
                </li>
            </ul>
            <a class="btn btn-primary" type="button" href="welcome.jsp">
                <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-box-arrow-right" viewBox="0 0 16 16">
                    <path fill-rule="evenodd" d="M10 12.5a.5.5 0 0 1-.5.5h-8a.5.5 0 0 1-.5-.5v-9a.5.5 0 0 1 .5-.5h8a.5.5 0 0 1 .5.5v2a.5.5 0 0 0 1 0v-2A1.5 1.5 0 0 0 9.5 2h-8A1.5 1.5 0 0 0 0 3.5v9A1.5 1.5 0 0 0 1.5 14h8a1.5 1.5 0 0 0 1.5-1.5v-2a.5.5 0 0 0-1 0v2z"></path>
                    <path fill-rule="evenodd" d="M15.854 8.354a.5.5 0 0 0 0-.708l-3-3a.5.5 0 0 0-.708.708L14.293 7.5H5.5a.5.5 0 0 0 0 1h8.793l-2.147 2.146a.5.5 0 0 0 .708.708l3-3z"></path>
                </svg>
                Logout
            </a>
        </div>
    </nav>

    <div class="tab-content ps-3 pe-3" id="myTabContent">
        <jsp:include page="home_tab.jsp"/>
        <jsp:include page="museums_tab.jsp"/>
        <jsp:include page="tours_tab.jsp"/>
        <jsp:include page="users_tab.jsp"/>
        <jsp:include page="logs_tab.jsp"/>
    </div>
</body>
</html>