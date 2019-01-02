package com.example.travello.controller;

import com.example.travello.entity.Account;
import com.example.travello.entity.Mail;
import com.example.travello.entity.Trip;
import com.example.travello.service.MailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Optional;

@Controller
@RequestMapping("/api/mail")
public class MailController {

    @Autowired
    MailService mailService;

    private static Logger logger = LoggerFactory.getLogger(MailController.class);

    @RequestMapping(value = "/send", method = RequestMethod.PUT)
    public ResponseEntity<Mail> sendMail(@RequestBody Mail mail){

        Mail mailData = mail;

        boolean success = mailService.sendMail(mailData);

        if(success) {
            logger.info("Email with title: {} send from address {}", mail.getSubject(), mail.getMail());
            return new ResponseEntity<>(mailData, HttpStatus.OK);
        } else {
            logger.info("Error while sendind e-mail");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }



    }

}