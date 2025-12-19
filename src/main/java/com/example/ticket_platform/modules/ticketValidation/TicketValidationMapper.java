package com.example.ticket_platform.modules.ticketValidation;

import com.example.ticket_platform.domain.entities.TicketValidation;
import com.example.ticket_platform.modules.ticketValidation.dto.TicketValidationDto;

public interface TicketValidationMapper {
    TicketValidationDto toDto(TicketValidation ticketValidation);
}
