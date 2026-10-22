package com.example.bdx_ss6.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "zones")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Zone {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Integer capacity;

    @Column(nullable = false)
    private  Integer occupiedSpots;

    @OneToMany(mappedBy = "zone")
    List<ParkingTicket> parkingTickets;
}
