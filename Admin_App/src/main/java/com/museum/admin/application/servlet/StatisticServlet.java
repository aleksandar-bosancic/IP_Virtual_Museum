package com.museum.admin.application.servlet;

import com.museum.admin.application.beans.UserStatisticBean;
import com.museum.admin.application.services.UserStatisticService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "Statistic", value = "/statistic")
public class StatisticServlet extends HttpServlet {
    private static final long serialVersionUID = -3432226116136797004L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserStatisticService statisticService = new UserStatisticService();
        List<UserStatisticBean> list = statisticService.findAllLastDay();
        for (UserStatisticBean user : list){
            System.out.println(user.getTimeActive().toInstant());
        }
    }
}
