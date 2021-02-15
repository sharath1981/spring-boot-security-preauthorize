package com.security.controller;

import java.security.Principal;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@PreAuthorize("isAuthenticated()")  
@RestController
public class HelloController {
	
	@PreAuthorize("permitAll()")
	@GetMapping
	public String sayHello() {
		return "Hello..."+getUsername();
	}
	
	@PreAuthorize("hasRole('USER')")
	@GetMapping("user")
	public String helloUser(Authentication authentication ) {
		return "Hello User..."+authentication.getName();
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping("admin")
	public String helloAdmin(Principal principal) {
		return "Hello Admin..."+principal.getName();
	}

	private String getUsername(){
		return SecurityContextHolder.getContext().getAuthentication().getName();
	}

}
