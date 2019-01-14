package com.example.travello.trip.rating;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Data
@NoArgsConstructor
@Entity
@Table(name = "ratings")
@Builder
@AllArgsConstructor
public class Rating {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    long id;
    long tripId;
    long userId;
    Double rate;

}
