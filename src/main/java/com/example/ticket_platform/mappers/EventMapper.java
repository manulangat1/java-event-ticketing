package com.example.ticket_platform.mappers;

import com.example.ticket_platform.domain.CreateTicketTypeRequest;
import com.example.ticket_platform.domain.dtos.CreateEventRequestDto;
import com.example.ticket_platform.domain.dtos.CreateEventResponseDto;
import com.example.ticket_platform.domain.dtos.CreateTicketTypeRequestDto;
import com.example.ticket_platform.domain.dtos.CreateTicketTypeResponseDto;
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
}
