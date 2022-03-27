<%@ page import="com.museum.admin.application.beans.UserBean" %>
<jsp:useBean id="userService" class="com.museum.admin.application.services.UserService"/>
<%  int id = Integer.parseInt(request.getParameter("id"));
    UserBean userBean = userService.findById(id);
    boolean status;
    if (userBean != null) {
        boolean isApproved = userBean.isApproved();
        status = userService.setApproval(id, !isApproved );
    }
    response.sendRedirect(request.getContextPath() + "/index.jsp");
%>
