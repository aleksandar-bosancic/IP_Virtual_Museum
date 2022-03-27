<%@ page import="com.museum.bank.admin.beans.BankAccountBean" %>
<%@ page import="com.museum.bank.admin.beans.TransactionBean" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:useBean id="account" class="com.museum.bank.admin.beans.BankAccountBean"/>
<%
    account = (BankAccountBean) request.getSession().getAttribute("account");
    List<TransactionBean> transactionList = (List<TransactionBean>) request.getSession().getAttribute("transactions");
%>
<!DOCTYPE html>
<html>
<head>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p"
            crossorigin="anonymous"></script>
    <script type="text/javascript" src="script/script.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
    <link rel="stylesheet" type="text/css" href="resources/style.css">
    <title>Home</title>
</head>
<body>
<div class="home-container">
    <div class="account-details_container">
        <div class="card-container">
            <div>
                <h3 class="card-text"><%=account.getHolderName()%>
                </h3>
            </div>
            <div>
                <h3 class="card-text"><%=account.getNumber()%>
                </h3>
            </div>
            <div>
                <h3 class="card-text">Balance: <%=account.getBalance()%>€</h3>
            </div>
            <div class="form-check form-switch" id="switch-button_container">
                <input class="form-check-input" type="checkbox" id="switchAccountBlocked" onclick="updateBlocked(<%=account.getId()%>)">
                <label class="form-check-label" for="switchAccountBlocked" style="color: white">Block payments</label>
            </div>
        </div>
    </div>
    <div class="transaction-list_container">
        <h2>Transactions</h2>
        <table class="table table-striped table-hover">
            <thead>
            <tr>
                <th scope="col">Transaction Id</th>
                <th scope="col">Time</th>
                <th scope="col">Amount (€)</th>
            </tr>
            </thead>
            <tbody>
            <%for (TransactionBean transaction : transactionList) { %>
            <tr>
                <td><%=transaction.getId()%>
                </td>
                <td><%=transaction.getTransactionTime()%>
                </td>
                <td><%=transaction.getAmount()%>
                </td>
            </tr>
            <%}%>
            </tbody>
        </table>
    </div>
</div>
</body>
</html>
