package com.example.travello.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@Table(name = "trips")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Trip {

    @Id
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

    @Column(columnDefinition = "varchar(8000)")
    private String description;

    @OneToMany(targetEntity=Card.class, mappedBy="trip", fetch=FetchType.LAZY, cascade = CascadeType.ALL)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private List<Card> cards;

    @JsonSerialize(using = ToStringSerializer.class)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    private LocalDate startDate;

    @JsonSerialize(using = ToStringSerializer.class)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    private LocalDate endDate;

    @ElementCollection(fetch=FetchType.EAGER)
    @CollectionTable(name = "trip_countries", joinColumns = @JoinColumn(name = "trip_id"))
    @Column(name = "country")
    private Set<String> countries = new HashSet<>();

    private String continent;

    private boolean isBusiness;

    private double sumRatings;
    private int sumVotes;

    @JsonProperty("countries")
    public Set<String> getCountries(){
        return countries;
    }

    @JsonProperty("countries")
    public void setCountries(Set<String> countries){
        this.countries = countries;
    }

}
