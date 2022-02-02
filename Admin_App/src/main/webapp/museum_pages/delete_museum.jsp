<jsp:useBean id="museumService" class="com.museum.admin.Admin_App.services.MuseumService"/>
<%  museumService.deleteById(Integer.parseInt(request.getParameter("id")));
    response.sendRedirect(request.getContextPath() + "/index.jsp"); %>
