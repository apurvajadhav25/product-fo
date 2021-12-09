package com.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.demo.model.Filter1;
import com.demo.model.Image;
import com.demo.repository.ImageRepository;

@RestController
@CrossOrigin
public class ImageController {
	@Autowired
	ImageRepository imageRepository;
	
	@GetMapping("/images")
	public  Iterable<Image> getAllImages(@RequestParam()int id){
		return imageRepository.findByImages(id);
	}
	
	@PostMapping("/images")
	public Image createImage(@RequestBody List<Image> filter1List) {
		  
		  Image savedImage = null;
		  for(Image image: filter1List) {
			  savedImage = imageRepository.save(image);
		  }
	    	 return savedImage;
	    }
	



}
