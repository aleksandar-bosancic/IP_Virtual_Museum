package com.museum.bank.admin.servlet.block;

import com.museum.bank.admin.services.BankAccountService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "Block", value = "/block")
public class Block extends HttpServlet {
    private static final long serialVersionUID = -8539167157645182726L;

    private final BankAccountService bankAccountService = new BankAccountService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        boolean status = false;
        String blocked = req.getParameter("blocked");
        int id = Integer.parseInt(req.getParameter("id"));
        if("true".equals(blocked)){
            status = bankAccountService.setBlocked(id, true);
        } else if ("false".equals(blocked)){
            status = bankAccountService.setBlocked(id, false);
        }
        if (!status){
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        } else {
            resp.setStatus(HttpServletResponse.SC_OK);
        }
    }
}
