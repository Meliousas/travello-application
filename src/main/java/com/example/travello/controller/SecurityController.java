package com.example.travello.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
public class SecurityController {

    private static Logger logger = LoggerFactory.getLogger(SecurityController.class);

    @GetMapping("/principal")
    public Principal user(Principal principal){
        logger.info("Principal requested: {}", principal.getName());
        return principal;
    }

}
