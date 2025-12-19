package com.example.ticket_platform.modules.ticketTypes;

import com.example.ticket_platform.domain.entities.Ticket;

import java.util.UUID;

public interface TicketTypeService {
    Ticket purchaseTicket (UUID userId, UUID ticketTypeId);
}
