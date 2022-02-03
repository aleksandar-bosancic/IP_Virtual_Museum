<jsp:useBean id="museumService" class="com.museum.admin.application.services.MuseumService"/>
<%  museumService.deleteById(Integer.parseInt(request.getParameter("id")));
    response.sendRedirect(request.getContextPath() + "/index.jsp"); %>
