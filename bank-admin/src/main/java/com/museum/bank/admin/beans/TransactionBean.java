package com.museum.bank.admin.beans;

import java.io.Serializable;
import java.sql.Timestamp;

public class TransactionBean implements Serializable {
    private static final long serialVersionUID = -1132727752589044870L;

    private Integer id;
    private Integer bankAccountId;
    private Double amount;
    private Timestamp transactionTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getBankAccountId() {
        return bankAccountId;
    }

    public void setBankAccountId(Integer bankAccountId) {
        this.bankAccountId = bankAccountId;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Timestamp getTransactionTime() {
        return transactionTime;
    }

    public void setTransactionTime(Timestamp transactionTime) {
        this.transactionTime = transactionTime;
    }
}
