package com.example.ticket_platform.controllers;


import com.example.ticket_platform.domain.CreateEventRequest;
import com.example.ticket_platform.domain.dtos.CreateEventRequestDto;
import com.example.ticket_platform.domain.dtos.CreateEventResponseDto;
import com.example.ticket_platform.domain.dtos.ListEventResponseDto;
import com.example.ticket_platform.domain.entities.Event;
import com.example.ticket_platform.mappers.EventMapper;
import com.example.ticket_platform.services.EventService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
//import org.hibernate.query.Page;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.UUID;

@Controller
@RequestMapping(path = "/api/v1/events")
@RequiredArgsConstructor
public class EventController {

    private final EventMapper eventMapper;
    private final EventService eventService;

    @PostMapping
    public ResponseEntity<CreateEventResponseDto> createEvent(
            @AuthenticationPrincipal Jwt jwt ,
            @Valid @RequestBody CreateEventRequestDto createEventRequestDto

            ){
        System.out.println("Serving the req:");
            CreateEventRequestDto createEventRequest= eventMapper.fromDto(createEventRequestDto);
            UUID userId =  UUID.fromString( jwt.getSubject());
            Event createdEvent =  eventService.createEvent(userId, createEventRequest);
            CreateEventResponseDto createEventResponseDto=  eventMapper.toDto(createdEvent);
        return  new ResponseEntity<>(
                createEventResponseDto,
                HttpStatus.CREATED
);

    }

    @GetMapping
    public  ResponseEntity<Page<ListEventResponseDto>> listEvents(
            @AuthenticationPrincipal Jwt jwt,
            Pageable pageable
    ) {
        UUID userId = parseUserId(jwt);
        Page<Event> events = eventService.listEventsForOrganizer(userId, pageable );
      return ResponseEntity.ok(
               events.map(eventMapper::toListEventResponseDto)
       );


    }

    private UUID parseUserId ( Jwt jwt) {
        return  UUID.fromString(jwt.getSubject());
    }
}
