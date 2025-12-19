package com.example.ticket_platform.modules.qrcode;

import com.example.ticket_platform.domain.entities.QRCode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface QrCodeRepository extends JpaRepository<QRCode,UUID> {
}
