package com.example.ticket_platform.modules.tickets;

import com.example.ticket_platform.domain.entities.Ticket;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TicketServiceImpl implements  TicketService {

private  final  TicketRepository ticketRepository;
    @Override
    public Page<Ticket> listAllUserTicket(UUID userId, Pageable pageable) {
        return   ticketRepository.findTicketsByUser(userId, pageable );
//        Page<Ticket> tickets = ticketRepository.findTicketsByUser(userId, pageable );
//        return  tickets;
    }
}
