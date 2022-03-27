<%@ page import="com.museum.admin.application.beans.LogsBean" %>
<jsp:useBean id="logsService" class="com.museum.admin.application.services.LogsService"/>
<div class="tab-pane fade" id="logs_panel" role="tabpanel" aria-labelledby="logs-tab">
    <table class="table table-hover" id="museums_table" style="margin-top: 3vh">
        <thead>
        <tr>
            <th scope="col">Log ID</th>
            <th scope="col">Username</th>
            <th scope="col">Category</th>
            <th scope="col">Label</th>
            <th scope="col">Timestamp</th>
        </tr>
        </thead>
        <tbody>
        <% for (LogsBean bean : logsService.findAll()) { %>
        <tr>
            <td><%=bean.getId()%></td>
            <td><%=bean.getUsername()%></td>
            <td><%=bean.getCategory()%></td>
            <td><%=bean.getLabel()%></td>
            <td><%=bean.getTimestamp()%></td>
        </tr>
        <% } %>
        </tbody>
    </table>
</div>