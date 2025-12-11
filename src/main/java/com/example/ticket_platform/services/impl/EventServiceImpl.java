package com.example.ticket_platform.services.impl;

import com.example.ticket_platform.domain.dtos.CreateEventRequestDto;
import com.example.ticket_platform.domain.entities.Event;
import com.example.ticket_platform.domain.entities.TicketType;
import com.example.ticket_platform.domain.entities.User;
import com.example.ticket_platform.exceptions.UserNotFoundException;
import com.example.ticket_platform.repositories.EventRepository;
import com.example.ticket_platform.repositories.UserRepository;
import com.example.ticket_platform.services.EventService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class EventServiceImpl implements EventService {
    private final UserRepository userRepository;
    private final EventRepository eventRepository;
    @Override
    public Event createEvent(UUID organizerId, CreateEventRequestDto event) {
       User organizer =  userRepository.findById(organizerId).orElseThrow(() -> new UserNotFoundException(
               String.format("User with ID '%s' not found", organizerId)
       ));
      List<TicketType> ticketTypes = event.getTicketTypes().stream().map(
               ticketType -> {
                   TicketType ticketTypeToCreate = new TicketType();
                   ticketTypeToCreate.setName(ticketType.getName());
                   ticketTypeToCreate.setPrice(ticketType.getPrice());
                   ticketTypeToCreate.setDescription(ticketType.getDescription());
                   ticketTypeToCreate.setTotalAvailable(ticketType.getTotalAvailable());
               return  ticketTypeToCreate;
               }
       ).toList();
       Event eventToCreate = new Event();
       eventToCreate.setName(event.getName());
       eventToCreate.setEnd(event.getEnd());
       eventToCreate.setStartTime(event.getStart());
       eventToCreate.setVenue(event.getVenue());
       eventToCreate.setSalesStart(event.getSalesStart());
       eventToCreate.setSalesEnd(event.getSalesEnd());
       eventToCreate.setStatus(event.getStatus());
       eventToCreate.setOrganizer(organizer);
       eventToCreate.setTicketTypes(ticketTypes);

       return  eventRepository.save(eventToCreate);
    }

    @Override
    public Page<Event> listEventsForOrganizer(UUID organizerId, Pageable pageable) {
        return  eventRepository.findByOrganizerId(organizerId, pageable);
    }

//    @Override
//    public Page<Event> listEventsForOrganizer(UUID organizerId, Pageable pageable) {
//        return  eventRepository.findByOrganizerId(organizerId, pageable);
//    }


}
