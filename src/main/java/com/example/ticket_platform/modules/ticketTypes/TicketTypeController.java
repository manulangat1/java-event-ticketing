package com.example.ticket_platform.modules.ticketTypes;


import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "api/v1/ticket-types")
public class TicketTypeController {

    private final TicketTypeService ticketTypeService;
//    @PostMapping("/{eventId}")
}
