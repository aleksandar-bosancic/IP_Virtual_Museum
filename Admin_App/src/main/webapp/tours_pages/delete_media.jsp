<jsp:useBean id="mediaService" class="com.museum.admin.Admin_App.services.MediaService"/>
<%  mediaService.deleteById(Integer.parseInt(request.getParameter("id")));
    response.sendRedirect("manage_presentation.jsp?id=" + request.getParameter("museumId")); %>