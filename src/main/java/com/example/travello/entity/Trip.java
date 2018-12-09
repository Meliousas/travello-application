package com.example.travello.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

    @JsonSerialize(using = ToStringSerializer.class)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    private LocalDate startDate;

    @JsonSerialize(using = ToStringSerializer.class)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    private LocalDate endDate;

    @ElementCollection
    @CollectionTable(name = "trip_countries", joinColumns = @JoinColumn(name = "trip_id"))
    @Column(name = "country")
    private Set<String> countries = new HashSet<>();

}
