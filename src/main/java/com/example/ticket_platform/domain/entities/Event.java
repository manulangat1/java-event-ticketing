package com.example.ticket_platform.domain.entities;


import com.example.ticket_platform.domain.enums.EventStatusEnum;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name="events")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Event {

    @Id
    @GeneratedValue( strategy = GenerationType.UUID)
    @Column( updatable = false, nullable = false)
    private UUID id;

    @Column( name = "name", nullable = false)
    private  String name;

    @Column( name = "start")
    private LocalDateTime startTime;

    @Column( name = "end")
    private LocalDateTime end;


    @Column( nullable = false)
    private String venue;


    @Column(name="sales_start", nullable = true)
    private LocalDateTime salesStart;

    @Column(name="sales_end", nullable = true)
    private LocalDateTime salesEnd;

    @Column(name="status", nullable = false)
    @Enumerated(EnumType.STRING)
    private EventStatusEnum status;


    @ManyToOne(
            fetch = FetchType.LAZY
    )
    @JoinColumn(
            name = "organizer_id"
    )
    private User organizer;



    @ManyToMany(
            mappedBy = "attendingEvents"
    )
    private List<User> attendees = new ArrayList<>();

    @ManyToMany(
            mappedBy = "staffingEvents"
    )
    private List<User> staff = new ArrayList<>();


    @OneToMany( mappedBy = "event", cascade = CascadeType.ALL)
    private List< TicketType> ticketTypes = new ArrayList<>();


    @CreatedDate
    @Column( name="created_at", updatable = false, nullable = false )
    private LocalDateTime createdAt;


    @LastModifiedDate
    @Column( name ="updated_at", nullable = false)
    private LocalDateTime updatedAt;


}
