package com.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
@Service
public class EmailSenderService {
	
	 @Autowired
	    private JavaMailSender mailSender;

	 

	    public void sendSimpleEmail(String body) {
	         //creating message
	        SimpleMailMessage message = new SimpleMailMessage();

	 

	        message.setFrom("anushkajadhav966@gmail.com");
	        message.setTo("apurvasuniljadhav123@gmail.com");
	        message.setText(body);
	        message.setSubject("Welcome");
	      //  message.setCc(cc);
	     //   message.setBcc(bcc);
	     //  sending message 
	        
	       // mailSender.send(message);
	        mailSender.send(message);
	        System.out.println("Mail Send...");
	    }
}
