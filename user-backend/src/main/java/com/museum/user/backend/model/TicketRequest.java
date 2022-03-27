package com.museum.user.backend.model;

import lombok.Data;

@Data
public class TicketRequest {
    private int tourId;
    private String username;
    private int transactionId;
}
