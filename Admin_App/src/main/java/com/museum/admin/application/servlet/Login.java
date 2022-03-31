package com.museum.admin.application.servlet;

import com.museum.admin.application.beans.AdminUserBean;
import com.museum.admin.application.services.AdminUserService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.commons.lang3.RandomStringUtils;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "Login", value = "/login")
public class Login extends HttpServlet {
    private static final long serialVersionUID = 4096368300897216562L;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        AdminUserService adminService = new AdminUserService();
        AdminUserBean adminAccount;
        List<AdminUserBean> adminList = adminService.findAll();
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String token = "";
        adminAccount = adminList.stream().filter(admin -> admin.getUsername().equals(username) && admin.getPassword().equals(password)).findFirst().orElse(null);
        if (adminAccount != null) {
            token = RandomStringUtils.random(15, true, true);
            adminService.updateToken(token, adminAccount.getId());
            resp.sendRedirect("index.jsp?token=" + token);
        } else {
            resp.sendRedirect("welcome.jsp");
        }
    }
}
