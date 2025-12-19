package com.example.ticket_platform.modules.tickets;

import com.example.ticket_platform.domain.entities.Ticket;
import com.example.ticket_platform.modules.tickets.dto.ListTicketDto;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(
        componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface TicketMapper {

    ListTicketDto toDto(Ticket ticket);
}
