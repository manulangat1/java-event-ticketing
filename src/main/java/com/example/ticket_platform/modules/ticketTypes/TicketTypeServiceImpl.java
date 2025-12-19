package com.example.ticket_platform.modules.ticketTypes;

import com.example.ticket_platform.domain.entities.Ticket;
import com.example.ticket_platform.domain.entities.TicketType;
import com.example.ticket_platform.domain.entities.User;
import com.example.ticket_platform.domain.enums.TicketStatusEnum;
import com.example.ticket_platform.exceptions.TicketSoldOutException;
import com.example.ticket_platform.exceptions.TicketTypeNotFoundException;
import com.example.ticket_platform.exceptions.UserNotFoundException;
import com.example.ticket_platform.modules.qrcode.QRCodeService;
import com.example.ticket_platform.repositories.TicketRepository;
import com.example.ticket_platform.repositories.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TicketTypeServiceImpl implements  TicketTypeService{
    private final UserRepository userRepository;
    private final TicketRepository ticketRepository;
    private final  TicketTypeRepository ticketTypeRepository;
    private final QRCodeService qrCodeService;


//    private final ticket
    @Override
    @Transactional
    public Ticket purchaseTicket(UUID userId, UUID ticketTypeId) {

        User user = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException(String.format("User with id %s not found", userId)));
//      TicketRepository ticket = ticketRepository.findByTicketId()
        TicketType ticketType = ticketTypeRepository.findByIdWithLock(ticketTypeId).orElseThrow(() -> new TicketTypeNotFoundException(String.format(
                "Ticket  type with id %s not found", ticketTypeId
        )));
        int purchasedTickets = ticketRepository.countByTicketTypeId(ticketTypeId);

        Integer totalAvailable = ticketType.getTotalAvailable();

        if (purchasedTickets + 1 > totalAvailable) {
            throw new TicketSoldOutException();
        }


        Ticket ticket = new Ticket();
        ticket.setStatus(TicketStatusEnum.PURCHASED);
        ticket.setTicketType(ticketType);
        ticket.setPurchaser(user);

        Ticket savedTicket = ticketRepository.save(ticket);
        qrCodeService.generateQrCode(savedTicket);

        ticketRepository.save(savedTicket);
        return null;

    }
}
