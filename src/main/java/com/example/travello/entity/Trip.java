package com.example.travello.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;

@Entity
@Data
@Table(name = "trips")
@AllArgsConstructor
@Builder
public class Trip {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @ManyToOne
    private Account account;

    @NotNull
    private String title;

    private String description;

    @NotNull
    private double publicRating = 0.0;

    @OneToMany(targetEntity=Card.class, mappedBy="trip", fetch=FetchType.EAGER)
    private List<Card> cards;

    private boolean isBlocked = false;

    private LocalDate startDate;
    private LocalDate endDate;

}
