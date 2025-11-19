package com.example.ticket_platform.domain.entities;


import com.example.ticket_platform.domain.enums.TicketValidationMethodEnum;
import com.example.ticket_platform.domain.enums.TicketValidationStatusEnum;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name="")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TicketValidation {

    @Id
    @GeneratedValue( strategy = GenerationType.UUID)
    @Column( updatable = false, nullable = false)
    private UUID id;

    @Column(
            nullable = false
    )
    @Enumerated(EnumType.STRING)
    private TicketValidationStatusEnum status;

    @Column(
            nullable = false
    )
    @Enumerated(EnumType.STRING)
    private TicketValidationMethodEnum ticketValidationMethodEnum;


    @CreatedDate
    @Column( name="created_at", updatable = false, nullable = false )
    private LocalDateTime createdAt;


    @LastModifiedDate
    @Column( name ="updated_at", nullable = false)
    private LocalDateTime updatedAt;
}
