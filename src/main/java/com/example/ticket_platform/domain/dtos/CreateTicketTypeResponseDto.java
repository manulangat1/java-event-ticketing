package com.example.ticket_platform.domain.dtos;

import java.time.LocalDateTime;
import java.util.UUID;

public class CreateTicketTypeResponseDto {

    private UUID id;
    private String name;
    private Double price;
    private String description;
    private  Integer totalAvailable;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
