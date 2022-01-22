<jsp:useBean id="museumService" class="com.museum.admin.Admin_App.services.MuseumService"/>
<%  if(museumService.deleteById(Integer.parseInt(request.getParameter("id"))))
    response.sendRedirect("../index.jsp"); %>
