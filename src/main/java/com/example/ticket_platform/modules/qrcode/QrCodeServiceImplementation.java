package com.example.ticket_platform.modules.qrcode;

import com.example.ticket_platform.domain.entities.QRCode;
import com.example.ticket_platform.domain.entities.Ticket;
import com.example.ticket_platform.domain.enums.QRCodeStatusEnum;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Base64;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class QrCodeServiceImplementation implements QRCodeService {
    private  final  QrCodeRepository qrCodeRepository;
    private final QRCodeWriter qrCodeWriter;
//    private
    @Override
    public QRCode generateQrCode(Ticket ticket) {
        try {
            UUID uniqueId = UUID.randomUUID();
            String qrCodeImage = generateQrCodeImage( uniqueId);
            QRCode qrCode = new QRCode();
            qrCode.setId(uniqueId);
            qrCode.setStatus(QRCodeStatusEnum.ACTIVE);
            qrCode.setValue(qrCodeImage);
            qrCode.setTicket(ticket);

          return   qrCodeRepository.saveAndFlush(qrCode);

        } catch (WriterException e) {
            throw new RuntimeException(e);
        }
    }

    private String generateQrCodeImage(UUID uniqueId) throws WriterException {

       BitMatrix bitMatrix= qrCodeWriter.encode(
                uniqueId.toString(),
                BarcodeFormat.QR_CODE,
                300,
                300
        );
      BufferedImage bufferedImage= MatrixToImageWriter.toBufferedImage(bitMatrix);

      try(ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
          ImageIO.write(bufferedImage, "PNG",baos);
       byte[] imageBytes =    baos.toByteArray();
       return Base64.getEncoder().encodeToString(imageBytes);
      } catch (IOException e) {
          throw new RuntimeException(e);
      }

    }
}
