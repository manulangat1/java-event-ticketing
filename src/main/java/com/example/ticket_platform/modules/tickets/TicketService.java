package com.example.ticket_platform.modules.tickets;

import com.example.ticket_platform.domain.entities.Ticket;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;
import java.util.UUID;

public interface TicketService {

    Page<Ticket> listAllUserTicket(UUID userId, Pageable pageable);

    Optional<Ticket> findById( UUID ticketID);
}
