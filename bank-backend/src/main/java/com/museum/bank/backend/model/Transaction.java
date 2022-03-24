package com.museum.bank.backend.model;

import lombok.*;

@Data
public class Transaction {
    private Double amount;
    private String transactionTime;
    private Integer bankAccountId;
}
