package com.example.ticket_platform.services;

import com.example.ticket_platform.domain.dtos.CreateEventRequestDto;
import com.example.ticket_platform.domain.entities.Event;

import java.util.UUID;

public interface EventService {

    Event createEvent(UUID organizerId, CreateEventRequestDto event);
}
