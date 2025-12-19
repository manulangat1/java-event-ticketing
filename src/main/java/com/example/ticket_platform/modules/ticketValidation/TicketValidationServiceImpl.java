package com.example.ticket_platform.modules.ticketValidation;

import com.example.ticket_platform.domain.entities.QRCode;
import com.example.ticket_platform.domain.entities.Ticket;
import com.example.ticket_platform.domain.entities.TicketValidation;
import com.example.ticket_platform.domain.enums.TicketValidationMethodEnum;
import com.example.ticket_platform.domain.enums.TicketValidationStatusEnum;
import com.example.ticket_platform.exceptions.QrCodeNotFoundException;
import com.example.ticket_platform.modules.qrcode.QrCodeRepository;
import com.example.ticket_platform.modules.tickets.TicketRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class TicketValidationServiceImpl implements  TicketValidationService {

    private final TicketValidationRepository ticketValidationRepository;
    private  final QrCodeRepository qrCodeRepository;
    private  final TicketRepository ticketRepository;

    @Override
    public TicketValidation validateTicketByQrCode(UUID qrCodeId) {
    QRCode qrCode = qrCodeRepository.findById(qrCodeId).orElseThrow(() -> new QrCodeNotFoundException(String.format(
                "Qr code with id %s not found", qrCodeId
        )));
    Ticket ticket = qrCode.getTicket();

    TicketValidation ticketValidation = new TicketValidation();
    ticketValidation.setTicket(ticket);
    ticketValidation.setTicketValidationMethodEnum(TicketValidationMethodEnum.QR_SCAN);

    ticketValidation.setStatus(TicketValidationStatusEnum.VALID);

    return  ticketValidation;
    }

    @Override
    public TicketValidation validateTicketManually(UUID ticketId) {
        Ticket ticket = ticketRepository.findById(ticketId).orElseThrow();
        QRCode qrCode = ticket.getQrCodes().getFirst();
        TicketValidation ticketValidation = new TicketValidation();
        ticketValidation.setTicket(ticket);
        ticketValidation.setTicketValidationMethodEnum(TicketValidationMethodEnum.MANUAL);

        ticketValidation.setStatus(TicketValidationStatusEnum.VALID);

        return  ticketValidation;
    }
}
