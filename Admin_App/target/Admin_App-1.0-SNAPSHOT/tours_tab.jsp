<%@ page import="com.museum.admin.Admin_App.beans.TourBean" %>
<%@ page import="java.util.List" %>
<%@ page import="com.museum.admin.Admin_App.beans.MuseumBean" %>
<jsp:useBean id="tourService" class="com.museum.admin.Admin_App.services.TourService"/>
<jsp:useBean id="museumService" class="com.museum.admin.Admin_App.services.MuseumService"/>

<% List<TourBean> tourList = tourService.findAll();
    List<MuseumBean> museumList = museumService.findAll();%>
<div class="tab-pane fade" id="tours_panel" role="tabpanel" aria-labelledby="tours-tab">
    <div class="mb-5" style="display: flex; flex-direction: row; justify-content: space-between;">
        <a class="btn btn-primary" href="tours_pages/add_tour.jsp" style="margin-top: 2vh">
            <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-plus-circle" viewBox="0 0 16 16">
                <path d="M8 15A7 7 0 1 1 8 1a7 7 0 0 1 0 14zm0 1A8 8 0 1 0 8 0a8 8 0 0 0 0 16z"></path>
                <path d="M8 4a.5.5 0 0 1 .5.5v3h3a.5.5 0 0 1 0 1h-3v3a.5.5 0 0 1-1 0v-3h-3a.5.5 0 0 1 0-1h3v-3A.5.5 0 0 1 8 4z"></path>
            </svg>
            Add tour
        </a>
        <a class="btn btn-primary" href="tours_pages/manage_presentation.jsp" style="margin-top: 2vh">
            <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-pencil-square" viewBox="0 0 16 16">
                <path d="M15.502 1.94a.5.5 0 0 1 0 .706L14.459 3.69l-2-2L13.502.646a.5.5 0 0 1 .707 0l1.293 1.293zm-1.75 2.456-2-2L4.939 9.21a.5.5 0 0 0-.121.196l-.805 2.414a.25.25 0 0 0 .316.316l2.414-.805a.5.5 0 0 0 .196-.12l6.813-6.814z"></path>
                <path fill-rule="evenodd" d="M1 13.5A1.5 1.5 0 0 0 2.5 15h11a1.5 1.5 0 0 0 1.5-1.5v-6a.5.5 0 0 0-1 0v6a.5.5 0 0 1-.5.5h-11a.5.5 0 0 1-.5-.5v-11a.5.5 0 0 1 .5-.5H9a.5.5 0 0 0 0-1H2.5A1.5 1.5 0 0 0 1 2.5v11z"></path>
            </svg>
            Manage presentations
        </a>
    </div>
    <table class="table" id="tours_table" style="margin-top: 3vh">
        <thead>
        <tr>
            <th scope="col">Museum</th>
            <th scope="col">Date</th>
            <th scope="col">Start time</th>
            <th scope="col">Duration(Hours)</th>
            <th scope="col">Price(€)</th>
            <th scope="col" style="width: 10%">Presentation</th>
            <th scope="col" style="width: 7%">Edit</th>
            <th scope="col" style="width: 7%">Remove</th>
        </tr>
        </thead>
        <tbody>
        <% for (TourBean tourBean : tourList) { %>
        <tr>
            <td><%=museumList.stream().filter(museumBean -> museumBean.getId() == tourBean.getMuseumId()).findFirst().orElse(null).getName()%></td>
            <td><%=tourBean.getDate()%></td>
            <td><%=tourBean.getTime()%></td>
            <td><%=tourBean.getDuration()%> hours</td>
            <td><%=tourBean.getPrice()%></td>
            <td>
                <a class="btn btn-primary" href="tours_pages/preview_presentation.jsp?id=<%=tourBean.getMuseumId()%>">
                    <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-film" viewBox="0 0 16 16">
                        <path d="M0 1a1 1 0 0 1 1-1h14a1 1 0 0 1 1 1v14a1 1 0 0 1-1 1H1a1 1 0 0 1-1-1V1zm4 0v6h8V1H4zm8 8H4v6h8V9zM1 1v2h2V1H1zm2 3H1v2h2V4zM1 7v2h2V7H1zm2 3H1v2h2v-2zm-2 3v2h2v-2H1zM15 1h-2v2h2V1zm-2 3v2h2V4h-2zm2 3h-2v2h2V7zm-2 3v2h2v-2h-2zm2 3h-2v2h2v-2z"></path>
                    </svg>
                    Show
                </a>
            </td>
            <td>
                <a class="btn btn-primary" href="tours_pages/manage_presentation.jsp?id=<%=tourBean.getMuseumId()%>">
                    <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-pencil-square" viewBox="0 0 16 16">
                        <path d="M15.502 1.94a.5.5 0 0 1 0 .706L14.459 3.69l-2-2L13.502.646a.5.5 0 0 1 .707 0l1.293 1.293zm-1.75 2.456-2-2L4.939 9.21a.5.5 0 0 0-.121.196l-.805 2.414a.25.25 0 0 0 .316.316l2.414-.805a.5.5 0 0 0 .196-.12l6.813-6.814z"></path>
                        <path fill-rule="evenodd" d="M1 13.5A1.5 1.5 0 0 0 2.5 15h11a1.5 1.5 0 0 0 1.5-1.5v-6a.5.5 0 0 0-1 0v6a.5.5 0 0 1-.5.5h-11a.5.5 0 0 1-.5-.5v-11a.5.5 0 0 1 .5-.5H9a.5.5 0 0 0 0-1H2.5A1.5 1.5 0 0 0 1 2.5v11z"></path>
                    </svg>
                    Edit
                </a>
            </td>
            <td>
                <a class="btn btn-primary" href="tours_pages/delete_tour.jsp?id=<%=tourBean.getId()%>">
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
    <div class="modal fade" id="modal" role="dialog">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title"><%%></h5>
                    <button type="button" class="btn-close" data-bs-dismiss="modal"></button>
                </div>
                <div class="modal-body">

                </div>
            </div>
        </div>
    </div>
</div>