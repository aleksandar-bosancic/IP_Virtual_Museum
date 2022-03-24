package com.museum.bank.backend.model;

import lombok.*;

@Data
@AllArgsConstructor
public class PaymentResponse {
    private boolean status;
    private Integer transactionId;
}
