package com.example.ticket_platform.mappers;

import com.example.ticket_platform.domain.CreateTicketTypeRequest;
import com.example.ticket_platform.domain.UpdateEventRequest;
import com.example.ticket_platform.domain.UpdateTicketTypeRequest;
import com.example.ticket_platform.domain.dtos.*;
import com.example.ticket_platform.domain.entities.Event;
import com.example.ticket_platform.domain.entities.TicketType;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper( componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface EventMapper {
    CreateTicketTypeRequest fromDto(CreateTicketTypeRequestDto dto) ;

    CreateEventRequestDto fromDto(CreateEventRequestDto dto);


    CreateEventResponseDto toDto(Event event);

    // REQUIRED so MapStruct can auto-map List<TicketType> → List<CreateTicketTypeResponseDto>
    CreateTicketTypeResponseDto toDto(TicketType ticketType);


    ListEventTicketTypesResponseDto toListEventTicketTypesDto( TicketType ticketType);

    ListEventResponseDto toListEventResponseDto(Event event);


    GetEventTicketTypesResponseDto toGetEventTicketTypesResponseDto(TicketType ticketType);

    GetEventDetailsEventResponseDto toGetEventDetailsEventResponseDto(Event event);

    UpdateTicketTypeRequest fromDto( UpdateTicketTypeRequestDto dto);

    UpdateEventRequest fromDto( UpdateEventRequestDto dto);

    UpdateTicketTypeResponseDto toUpdateTicketTypeResponseDto( TicketType ticketType);

    UpdateEventResponseDto toUpdateEventResponseDto(Event event);

  ListPublishedEventResponseDto toListPublishedEventResponseDto( Event event);
}
