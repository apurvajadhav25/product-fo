package com.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
@Service
public class EmailSenderService {
	
	 @Autowired
	    private JavaMailSender mailSender;

	 

	    public void sendSimpleEmail(String toEmail,
	                                String body,
	                                String subject) {
	         //creating message
	        SimpleMailMessage message = new SimpleMailMessage();

	 

	        message.setFrom("apurvasuniljadhav123@gmail.com");
	        message.setTo(toEmail);
	        message.setText(body);
	        message.setSubject(subject);
	      //  message.setCc(cc);
	     //   message.setBcc(bcc);
	     //  sending message 
	        
	       // mailSender.send(message);
	        mailSender.send(message);
	        System.out.println("Mail Send...");
	    }
}
