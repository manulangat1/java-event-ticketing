package com.example.ticket_platform.domain;

import java.util.UUID;

public class UpdateTicketTypeRequest {
    private UUID id;
    private String name;
    private  Double price;
    private String description;
    private  Integer totalAvailable;
}
