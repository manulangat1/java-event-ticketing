package com.example.ticket_platform.repositories;

import com.example.ticket_platform.domain.entities.Event;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import java.util.Optional;
import java.util.UUID;

@Repository
public interface EventRepository extends JpaRepository<Event, UUID> {

    Page<Event> findByOrganizerId(UUID organizerId, Pageable pageable);
    Optional<Event> findByIdAndOrganizerId( UUID id, UUID organizerId);
//    Optional<Event> getEventForOrganizer( UUID organizerId, UUID id);
}
