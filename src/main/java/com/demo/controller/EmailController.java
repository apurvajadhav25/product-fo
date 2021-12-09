package com.demo.controller;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.demo.service.EmailSenderService;

public class EmailController {
	
	@Autowired
	private EmailSenderService service;
	
	@GetMapping("/sendemail")
	public String sendmail(@RequestParam("name") String name,
			               @RequestParam("email") String email,
			               @RequestParam("message") String message) throws MessagingException{
		String body="Name: "+name+"\n"+
				    "Email: "+email+"\n"+
				    "Message: "+message;
		service.sendSimpleEmail(body);
		return "Email sent successfully";
	}	

}
