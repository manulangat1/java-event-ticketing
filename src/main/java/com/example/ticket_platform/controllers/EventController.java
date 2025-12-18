package com.example.ticket_platform.controllers;


import com.example.ticket_platform.domain.CreateEventRequest;
import com.example.ticket_platform.domain.UpdateEventRequest;
import com.example.ticket_platform.domain.dtos.*;
import com.example.ticket_platform.domain.entities.Event;
import com.example.ticket_platform.mappers.EventMapper;
import com.example.ticket_platform.services.EventService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
//import org.hibernate.query.Page;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
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

    @GetMapping(path = "/{eventId}/")
    public  ResponseEntity<GetEventDetailsEventResponseDto> getEvent (
            @AuthenticationPrincipal Jwt jwt,
            @PathVariable UUID eventId
    ) {
        UUID userId =  parseUserId(jwt);
       return  eventService.getEventForOrganizer(userId, eventId)
               .map(eventMapper::toGetEventDetailsEventResponseDto)
               .map(ResponseEntity::ok)
               .orElse(ResponseEntity.notFound().build());

    }


    @PutMapping(path="/{eventId}")
    public  ResponseEntity<UpdateEventResponseDto> updateEvent (
            @AuthenticationPrincipal Jwt jwt,
            @PathVariable UUID eventId,
            @Valid @RequestBody UpdateEventRequestDto dto
    ) {
        UUID userId = parseUserId(jwt);
        UpdateEventRequest updateEventRequest = eventMapper.fromDto(
                dto
        );
             Event updatedEvent =  eventService.updateEventForOrganizer(
                        userId, eventId,updateEventRequest
                );
             UpdateEventResponseDto responseDto= eventMapper.toUpdateEventResponseDto(updatedEvent);
             return  new ResponseEntity<>(
                     responseDto,
                     HttpStatus.OK
             );

    }

    @DeleteMapping("/{eventId}")
    public  ResponseEntity<Void> deleteEvent (
            @AuthenticationPrincipal Jwt jwt,
            @PathVariable UUID eventId
//            @Valid @RequestBody
    ) {
        UUID userID = parseUserId(jwt);
        eventService.deleteEventForOrganizer(userID, eventId);
        return  ResponseEntity.noContent().build();

    }

    @GetMapping("/published")
    public  ResponseEntity<Page<ListPublishedEventResponseDto>> listPublishedEvents (
            @AuthenticationPrincipal Jwt jwt,
            Pageable pageable,
            @RequestParam( required = false) String q
    ) {
//        UUID userId = parseUserId(jwt);
        Page<Event> events;

        if ( null != q && !q.trim().isEmpty()) {
            events = eventService.searchPublishedEvents(q, pageable);
        } else {
            events = eventService.listPublishedEvents(pageable);
        }

//        Page<Event> events = eventService.listPublishedEvents(pageable);
        return  ResponseEntity.ok(
                events.map(
                        eventMapper::toListPublishedEventResponseDto
                ));


    }

//    @GetMapping('/published/searc')
    private UUID parseUserId ( Jwt jwt) {
        return  UUID.fromString(jwt.getSubject());
    }
}
