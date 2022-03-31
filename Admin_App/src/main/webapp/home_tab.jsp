<%@ page import="com.museum.admin.application.services.UserStatisticService" %>
<%@ page import="com.museum.admin.application.beans.UserStatisticBean" %>
<%@ page import="java.util.List" %>
<jsp:useBean id="userService" class="com.museum.admin.application.services.UserService"/>
<jsp:useBean id="statisticService" class="com.museum.admin.application.services.UserStatisticService"/>
<% List<UserStatisticBean> userStatisticList = statisticService.findAllLastDay(); %>
<script type="text/javascript">
    function drawChart() {
        // $.ajax({
        //     type: 'GET',
        //     url: 'http://localhost:8080/Admin_App_war_exploded/statistic',
        //     data: JSON,
        //     success: function (data) {
        //         console.log(data)
        //         let times = data.map(value => value.timeActive);
        //         $.each(times, function (index, value){
        //             console.log(value);
        //         })
        //     }
        // });

        let data = [1, 2, 3 ,4, 0, 9, 29, 30, 13, 12, 9, 12, 13, 11, 9, 8, 6, 7, 2, 3, 5, 3];
        let hours = ['01:00', '02:00', '03:00', '04:00', '05:00', '06:00', '07:00', '08:00', '09:00', '10:00', '11:00', '12:00', '13:00',
            '14:00', '15:00', '16:00', '17:00', '18:00', '19:00', '20:00', '21:00', '22:00', '23:00', '00:00'];
        new Chart('userStatistic', {
            type: 'bar',
            data: {
                labels: hours,
                datasets: [{
                    label: 'Active users',
                    data: data
                }]
            },
        });
    }
</script>
<div class="tab-pane fade show active" id="home_panel" role="tabpanel" aria-labelledby="home-tab">
    <div style="display: flex; flex-direction: row;">
        <h2>Total users: </h2>
        <h2> <%=userService.getTotalNumberOfUsers()%></h2>
    </div>
    <div style="display: flex; flex-direction: row;">
        <h2>Active users: </h2>
        <h2> <%=userService.getNumberOfActiveUsers()%></h2>
    </div>
    <canvas id="userStatistic" width="800" height="400"></canvas>
    <input name="size" id="size" value="<%=userStatisticList.size()%>" hidden>
    <% for(int i = 0; i < userStatisticList.size(); i ++) { %>
        <input name="i" value="<%=userStatisticList.get(i).getTimeActive()%>" id="i" hidden>
    <% }%>
</div>