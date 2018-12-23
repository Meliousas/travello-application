package com.example.travello;

import com.example.travello.entity.Account;
import com.example.travello.entity.CustomUserDetails;
import com.example.travello.entity.Role;
import com.example.travello.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.Arrays;

@SpringBootApplication
@EnableConfigurationProperties
public class TravelloApplication {

	public static void main(String[] args) {
		SpringApplication.run(TravelloApplication.class, args);
	}

//	@Autowired
//	public void authenticationManager(AuthenticationManagerBuilder builder, AccountRepository repo) throws Exception{
////		Role role = new Role("USER");
//////		if(repo.count() == 0){
//////			repo.save(new Account("user","user","email",
//////					false, Arrays.asList(role)));
//////		}
//		builder.userDetailsService( s -> new CustomUserDetails(repo.findByUsername(s));
//	}
}
