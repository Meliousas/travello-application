package com.example.travello.account.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
public class SecurityController {

    private static Logger logger = LoggerFactory.getLogger(SecurityController.class);


    @GetMapping("/principal")
    public ResponseEntity<Principal> user(Principal principal){
        if(principal == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        logger.info("Principal requested: {}", principal.getName());
        return ResponseEntity.ok(principal);
    }

}
