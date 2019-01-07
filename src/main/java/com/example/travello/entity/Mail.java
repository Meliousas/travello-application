package com.example.travello.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Mail {

    public String name;

    private String email = "travello.contact@gmail.com";

    private String subject;

    private String message;

    private Long userId;

    private String userEmail;

    private Long tripId;

    public Mail(String name, String email, String subject, String message) {
        this.name = name;
        this.email = email;
        this.subject = subject;
        this.message = message;
    }
}
