package com.example.ticket_platform.modules.ticketTypes;

import com.example.ticket_platform.domain.entities.TicketType;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;
import java.util.UUID;

public interface TicketTypeRepository extends JpaRepository<TicketType, UUID> {
//create an optimistic lock here.
    @Query("SELECT tt FROM TicketType WHERE  tt.id = :id ")
    @Lock(
            LockModeType.PESSIMISTIC_WRITE
    )
    Optional<TicketType> findByIdWithLock(
            @Param("id") UUID id
    );
    Optional<TicketType> findByTicketTypeId( UUID id);


//    implements functionality for ticket validation.


}
