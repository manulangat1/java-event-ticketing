package com.example.ticket_platform.domain.dtos;

import com.example.ticket_platform.domain.enums.EventStatusEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetEventDetailsEventResponseDto {
    private UUID id;
    private String name;
    private LocalDateTime startTime;
    private LocalDateTime end;
    private LocalDateTime salesStater;
    private LocalDateTime salesEnd;
    private EventStatusEnum status;
    private List<GetEventTicketTypesResponseDto> ticketTypes = new ArrayList<>();
    private LocalDateTime updatedAt;
    private LocalDateTime createdAt;

}
