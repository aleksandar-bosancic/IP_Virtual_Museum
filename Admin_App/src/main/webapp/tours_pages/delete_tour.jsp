<jsp:useBean id="tourService" class="com.museum.admin.Admin_App.services.TourService"/>
<%  tourService.deleteById(Integer.parseInt(request.getParameter("id")));
    response.sendRedirect("../index.jsp"); %>