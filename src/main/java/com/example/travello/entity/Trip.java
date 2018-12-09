package com.example.travello.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.annotation.Nullable;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;

@Entity
@Data
@Table(name = "trips")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Trip {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @ManyToOne
    @JsonIgnore
    private Account account;

    @NotNull
    private String title;

    @NotNull
    @Enumerated(EnumType.ORDINAL)
    private TripStatus status;

    private String description;

    @NotNull
    private double publicRating = 0.0;

    @OneToMany(targetEntity=Card.class, mappedBy="trip", fetch=FetchType.EAGER)
    private List<Card> cards;

    private LocalDate startDate;
    private LocalDate endDate;

}
