package com.example.travello.controller;

import com.example.travello.entity.Account;
import com.example.travello.entity.CustomUserDetails;
import com.example.travello.service.AccountService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.endpoint.TokenEndpoint;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.Map;

@RestController
public class SecurityController {

    private static Logger logger = LoggerFactory.getLogger(SecurityController.class);

    @Autowired
    AccountService accountService;

    @GetMapping("/principal")
    public ResponseEntity<Principal> user(Principal principal){
        if(principal == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        logger.info("Principal requested: {}", principal.getName());
        return ResponseEntity.ok(principal);
    }

//    @PostMapping("/api/login")
//    public ResponseEntity<OAuth2AccessToken> login(Principal principal, @RequestParam
//            Map<String, String> parameters) throws HttpRequestMethodNotSupportedException {
//        TokenEndpoint te = new TokenEndpoint();
//        String userEmail = parameters.get("username");
//
//        if(accountService.getAccountByEmail(userEmail).filter(Account::changeStatus).isPresent()){
//            return te.postAccessToken(principal, parameters);
//        } else {
//           return new ResponseEntity<>(HttpStatus.FORBIDDEN);
//        }
//
//    }
}
