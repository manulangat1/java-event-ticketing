package com.example.ticket_platform.modules.ticketTypes;


import com.example.ticket_platform.shared.ParseToUser;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "api/v1/ticket-types")
public class TicketTypeController {

    private final TicketTypeService ticketTypeService;
    private final ParseToUser parseToUser;
    @PostMapping("/{ticketTypeId}/tickets")
    public ResponseEntity<Void> handlePurchaseTicket(
            @AuthenticationPrincipal Jwt jwt,
            @PathVariable UUID ticketTypeId
            ){
        UUID userId = parseToUser.parseToUser(jwt);
        ticketTypeService.purchaseTicket(
userId, ticketTypeId
        );

        return   new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }

}
