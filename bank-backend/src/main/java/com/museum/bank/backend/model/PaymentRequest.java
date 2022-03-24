package com.museum.bank.backend.model;

import lombok.Data;

@Data
public class PaymentRequest {
    private String holderName;
    private String number;
    private Type type;
    private String validThru;
    private String pin;
    private double amount;
}
