package com.example.ticket_platform.modules.qrcode;

import com.example.ticket_platform.domain.entities.QRCode;
import com.example.ticket_platform.domain.entities.Ticket;

public interface QRCodeService {
    QRCode generateQrCode(Ticket ticket);
}
