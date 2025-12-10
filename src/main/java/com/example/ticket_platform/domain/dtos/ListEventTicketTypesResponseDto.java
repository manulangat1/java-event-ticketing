package com.example.ticket_platform.domain.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ListEventTicketTypesResponseDto {

    private UUID id;

    private String name;
    private Double price;
    private String description;
    private  Integer totalAvailable;

}
