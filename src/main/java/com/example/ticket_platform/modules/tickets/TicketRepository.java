package com.example.ticket_platform.modules.tickets;

import com.example.ticket_platform.domain.entities.Ticket;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, UUID> {

    Optional<Ticket> findByTicketId(UUID id);
    int countByTicketTypeId(UUID ticketTypeId);

    Page<Ticket> findTicketsByUser(UUID userId, Pageable pageable);
}
