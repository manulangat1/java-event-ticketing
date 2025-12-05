package com.example.ticket_platform.services;

import com.example.ticket_platform.domain.CreateEventRequest;
import com.example.ticket_platform.domain.entities.Event;

import java.util.UUID;

public interface EventService {

    Event createEvent(UUID organizerId, CreateEventRequest event);
}
