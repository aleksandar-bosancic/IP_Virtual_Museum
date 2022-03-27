package com.museum.bank.admin.servlet.login;

import com.museum.bank.admin.beans.BankAccountBean;
import com.museum.bank.admin.beans.TransactionBean;
import com.museum.bank.admin.services.BankAccountService;
import com.museum.bank.admin.services.TransactionService;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "Login", value = "/login")
public class Login extends HttpServlet {
    private static final long serialVersionUID = -4205539298210873201L;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        BankAccountService bankAccountService = new BankAccountService();
        TransactionService transactionService = new TransactionService();
        BankAccountBean loginBean = new BankAccountBean();
        BankAccountBean account;
        loginBean.setNumber(request.getParameter("card_number"));
        loginBean.setHolderName(request.getParameter("holder_name"));
        loginBean.setPin(request.getParameter("pin"));
        if ((account = bankAccountService.login(loginBean)) != null) {
            request.getSession().setAttribute("account", account);
            List<TransactionBean> transactionList = transactionService.findAllByBankAccountId(account.getId());
            request.getSession().setAttribute("transactions", transactionList);
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("/WEB-INF/pages/home.jsp");
            requestDispatcher.forward(request, response);
        } else {
            response.sendRedirect("/");
        }
    }
}
