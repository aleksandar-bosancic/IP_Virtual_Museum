<%@ page import="com.museum.admin.application.beans.UserBean" %>
<%@ page import="org.apache.commons.lang3.RandomStringUtils" %>
<jsp:useBean id="userService" class="com.museum.admin.application.services.UserService"/>
<%  int id = Integer.parseInt(request.getParameter("id"));
    UserBean userBean = userService.findById(id);
    boolean status;
    String newPassword = RandomStringUtils.random(15, true, true);
    if (userBean != null) {
        status = userService.updatePassword(id, newPassword);
    }
    response.sendRedirect(request.getContextPath() + "/index.jsp");
%>