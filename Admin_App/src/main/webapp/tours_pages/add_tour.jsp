<%@ page import="com.museum.admin.Admin_App.beans.MuseumBean" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:useBean id="museumService" class="com.museum.admin.Admin_App.services.MuseumService"/>
<jsp:useBean id="museumBean" class="com.museum.admin.Admin_App.beans.MuseumBean"/>
<jsp:useBean id="tourService" class="com.museum.admin.Admin_App.services.TourService"/>
<jsp:useBean id="tourBean" class="com.museum.admin.Admin_App.beans.TourBean"/>
<jsp:setProperty name="tourBean" property="*"/>
<% List<MuseumBean> museumList = museumService.findAll(); %>

<!DOCTYPE html>
<%  if (request.getParameter("submit") != null) {
    if (request.getParameter("museumId") == null){ %>
        <script type="text/javascript">
            alert("Select museum!");
        </script>
    <% } else {
        tourService.insert(tourBean);
        response.sendRedirect("../index.jsp");
    }
} %>
<html>
<head>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>

    <title>Add Tour</title>
</head>
<body style="background-color: #e3f2fd">
<section class="container-fluid" style="height: 100vh; display: flex; align-items: center; justify-items: center; justify-content: center; align-content: center">
    <form method="post" action="add_tour.jsp" style="display: flex; flex-direction: column; align-items: center">
        <div class="mb-5" style="width: 50vh; display: flex; flex-direction: row; justify-content: space-around">
            <div class="mb-3">
                <select class="form-select" id="museumId" name="museumId">
                    <option selected disabled>Select museum</option>
                    <% for (MuseumBean museum : museumList) { %>
                    <option value="<%= museum.getId() %>"><%= museum.getName() %></option>
                    <% } %>
                </select>
            </div>
        </div>
        <div style="width: 100vh; display: flex; flex-direction: row; justify-content: space-evenly">
            <div>
                <div class="mb-5" style="width: 25vh; display: flex; flex-direction: column;">
                    <label for="date">Date:</label>
                    <input class="form-control" type="date" id="date" name="date" required>
                </div>
                <div class="mb-5" style="width: 25vh; display: flex; flex-direction: column;">
                    <label for="time">Start time:</label>
                    <input class="form-control" type="time" id="time" name="time" required>
                </div>
            </div>
            <div>
                <div class="mb-5" style="width: 25vh; display: flex; flex-direction: column;">
                    <label for="duration">Duration(hours):</label>
                    <input class="form-control" type="number" id="duration" name="duration" step="0.1" required>
                </div>
                <div class="mb-5" style="width: 25vh; display: flex; flex-direction: column;">
                    <label for="price">Price(€):</label>
                    <input class="form-control" type="number" id="price" name="price" step="0.1" required>
                </div>
            </div>
        </div>
        <button type="submit" class="btn btn-primary w-25" name="submit">Add Tour</button>
    </form>
</section>
</body>
</html>
