<%@ page import="com.museum.admin.Admin_App.beans.MuseumBean" %>
<%@ page import="java.util.List" %>
<jsp:useBean id="museumBean" class="com.museum.admin.Admin_App.beans.MuseumBean" scope="page"/>
<jsp:useBean id="museumService" class="com.museum.admin.Admin_App.services.MuseumService" scope="page"/>

<div class="tab-pane fade" id="museums_panel" role="tabpanel" aria-labelledby="museums-tab">
    <a class="btn btn-primary" href="add_museum.jsp" style="margin-top: 2vh">Add museum</a>
    <table class="table" id="museums_table" style="margin-top: 3vh">
        <thead>
        <tr>
            <th>Name</th>
            <th>Address</th>
            <th>Phone number</th>
            <th>Country</th>
            <th>City</th>
            <th>Type</th>
            <th>Edit</th>
            <th>Remove</th>
        </tr>
        </thead>
        <tbody>
        <% for (MuseumBean bean : museumService.findAll()) { %>
        <tr>
            <td><%=bean.getName()%></td>
            <td><%=bean.getAddress()%></td>
            <td><%=bean.getPhoneNumber()%></td>
            <td><%=bean.getCountry()%></td>
            <td><%=bean.getCity()%></td>
            <td><%=bean.getType()%></td>
            <td>
                <a class="btn btn-primary" href="update_museum.jsp?id=<%=bean.getId()%>">
                    <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-pencil-square" viewBox="0 0 16 16">
                        <path d="M15.502 1.94a.5.5 0 0 1 0 .706L14.459 3.69l-2-2L13.502.646a.5.5 0 0 1 .707 0l1.293 1.293zm-1.75 2.456-2-2L4.939 9.21a.5.5 0 0 0-.121.196l-.805 2.414a.25.25 0 0 0 .316.316l2.414-.805a.5.5 0 0 0 .196-.12l6.813-6.814z"></path>
                        <path fill-rule="evenodd" d="M1 13.5A1.5 1.5 0 0 0 2.5 15h11a1.5 1.5 0 0 0 1.5-1.5v-6a.5.5 0 0 0-1 0v6a.5.5 0 0 1-.5.5h-11a.5.5 0 0 1-.5-.5v-11a.5.5 0 0 1 .5-.5H9a.5.5 0 0 0 0-1H2.5A1.5 1.5 0 0 0 1 2.5v11z"></path>
                    </svg>
                    Edit
                </a>
            </td>
            <td>
                <a class="btn btn-primary">
                    <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-trash-fill" viewBox="0 0 16 16">
                        <path d="M2.5 1a1 1 0 0 0-1 1v1a1 1 0 0 0 1 1H3v9a2 2 0 0 0 2 2h6a2 2 0 0 0 2-2V4h.5a1 1 0 0 0 1-1V2a1 1 0 0 0-1-1H10a1 1 0 0 0-1-1H7a1 1 0 0 0-1 1H2.5zm3 4a.5.5 0 0 1 .5.5v7a.5.5 0 0 1-1 0v-7a.5.5 0 0 1 .5-.5zM8 5a.5.5 0 0 1 .5.5v7a.5.5 0 0 1-1 0v-7A.5.5 0 0 1 8 5zm3 .5v7a.5.5 0 0 1-1 0v-7a.5.5 0 0 1 1 0z"></path>
                    </svg>
                    Remove
                </a>
            </td>
        </tr>
        <% } %>
        </tbody>
    </table>
</div>