<%@ page import="com.museum.admin.application.beans.UserBean" %>
<jsp:useBean id="userService" class="com.museum.admin.application.services.UserService"/>

<div class="tab-pane fade" id="users_panel" role="tabpanel" aria-labelledby="users-tab">
    <table class="table table-hover" id="museums_table" style="margin-top: 3vh">
        <thead>
        <tr>
            <th scope="col">Username</th>
            <th scope="col">Password</th>
            <th scope="col">First Name</th>
            <th scope="col">Last Name</th>
            <th scope="col">Email</th>
            <th scope="col">Approved</th>
            <th scope="col" style="width: 10%">Edit</th>
            <th scope="col" style="width: 10%">Reset password</th>
        </tr>
        </thead>
        <tbody>
        <% for (UserBean bean : userService.findAll()) { %>
        <tr>
            <td><%=bean.getUsername()%></td>
            <td><%=bean.getPassword()%></td>
            <td><%=bean.getName()%></td>
            <td><%=bean.getLastName()%></td>
            <td><%=bean.getEmail()%></td>
            <td><%=bean.isApproved()%></td>
            <td>
                <a class="btn btn-primary" href="user_pages/edit_approval.jsp?id=<%=bean.getId()%>">
                    <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-pencil-square" viewBox="0 0 16 16">
                        <path d="M15.502 1.94a.5.5 0 0 1 0 .706L14.459 3.69l-2-2L13.502.646a.5.5 0 0 1 .707 0l1.293 1.293zm-1.75 2.456-2-2L4.939 9.21a.5.5 0 0 0-.121.196l-.805 2.414a.25.25 0 0 0 .316.316l2.414-.805a.5.5 0 0 0 .196-.12l6.813-6.814z"></path>
                        <path fill-rule="evenodd" d="M1 13.5A1.5 1.5 0 0 0 2.5 15h11a1.5 1.5 0 0 0 1.5-1.5v-6a.5.5 0 0 0-1 0v6a.5.5 0 0 1-.5.5h-11a.5.5 0 0 1-.5-.5v-11a.5.5 0 0 1 .5-.5H9a.5.5 0 0 0 0-1H2.5A1.5 1.5 0 0 0 1 2.5v11z"></path>
                    </svg>
                    <%if (bean.isApproved()) {%>
                    Block <%} else {%>
                    Approve
                    <%}%>
                </a>
            </td>
            <td>
                <a class="btn btn-primary" href="user_pages/reset_password.jsp?id=<%=bean.getId()%>">
                    <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-arrow-clockwise" viewBox="0 0 16 16">
                        <path fill-rule="evenodd" d="M8 3a5 5 0 1 0 4.546 2.914.5.5 0 0 1 .908-.417A6 6 0 1 1 8 2v1z"></path>
                        <path d="M8 4.466V.534a.25.25 0 0 1 .41-.192l2.36 1.966c.12.1.12.284 0 .384L8.41 4.658A.25.25 0 0 1 8 4.466z"></path>
                    </svg>
                    Reset
                </a>
            </td>
        </tr>
        <% } %>
        </tbody>
    </table>
</div>