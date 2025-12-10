package com.example.ticket_platform.services;

import com.example.ticket_platform.domain.dtos.CreateEventRequestDto;
import com.example.ticket_platform.domain.entities.Event;
import org.springframework.data.domain.Page;

import java.awt.print.Pageable;
import java.util.UUID;

public interface EventService {

    Event createEvent(UUID organizerId, CreateEventRequestDto event);
    Page<Event> listEventsForOrganizer(UUID organizerId, Pageable pageable);
}
