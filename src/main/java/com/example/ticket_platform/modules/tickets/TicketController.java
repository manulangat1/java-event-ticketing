package com.example.ticket_platform.modules.tickets;


import com.example.ticket_platform.domain.entities.Ticket;
import com.example.ticket_platform.modules.tickets.dto.ListTicketDto;
import com.example.ticket_platform.shared.ParseToUser;
import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping(path="/api/v1/tickets")
public class TicketController {


    private final TicketService ticketService;
    private final ParseToUser parseToUser;
    private final  TicketMapper ticketMapper;

    @GetMapping
    public ResponseEntity<Page<ListTicketDto>> listAllUserTickets (
            @AuthenticationPrincipal()Jwt jwt,
            Pageable pageable
            ) {

        UUID userId = parseToUser.parseToUser(jwt);
        Page<Ticket> tickets = ticketService.listAllUserTicket(userId,pageable);
        return  ResponseEntity.ok(
                tickets.map(
                        ticketMapper::toDto
                )
        );

    }
}
