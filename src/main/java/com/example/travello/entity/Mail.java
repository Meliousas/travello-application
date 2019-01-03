package com.example.travello.entity;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Mail {

    public String name;

    private String mail;

    private String subject;

    private String message;

}
