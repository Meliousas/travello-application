package com.example.travello.mail;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.io.IOException;

@Controller
@RequestMapping("/api/mail")
public class MailController {

    @Autowired
    MailService mailService;

    @Autowired
    ObjectMapper objectMapper;

    private static Logger logger = LoggerFactory.getLogger(MailController.class);

    @RequestMapping(value = "/send", method = RequestMethod.PUT)
    public ResponseEntity sendMail(@RequestBody String mail) throws IOException {

        Mail mailData = objectMapper.readValue(mail, Mail.class);

        boolean success = mailService.sendMail(mailData);

        if(success) {
            logger.info("Email with title: {} send from address {}", mailData.getSubject(), mailData.getEmail());
            return ResponseEntity.ok().build();
        } else {
            logger.info("Error while sendind e-email");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }



    }

}
