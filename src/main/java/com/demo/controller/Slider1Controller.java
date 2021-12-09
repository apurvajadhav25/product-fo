package com.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.model.Filter1;
import com.demo.model.Slider1;
import com.demo.repository.Filter1Repository;
import com.demo.repository.Slider1Repository;

@RestController
@CrossOrigin
public class Slider1Controller {
	
	@Autowired
		Slider1Repository slider1Repository;
	
	@GetMapping("/slider1")
	public  Iterable<Slider1> getAllSlider1(){
		return  slider1Repository.findAll();
	}

}
