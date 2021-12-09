package com.demo.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.demo.model.Filter1;
import com.demo.repository.Filter1Repository;

@RestController
@CrossOrigin
public class Filter1Controller {
	@Autowired
	Filter1Repository filter1Repository;
	
	@GetMapping("/filter1")
	public  Iterable<Filter1> getAllFilter1(){
		return  filter1Repository.findFilters();
	}
	
	@PostMapping("/filter1")
	public Filter1 createFilter1(@RequestBody List<Filter1> filter1List) {
		  
		  Filter1 savedFilter = null;
		  for(Filter1 filter: filter1List) {
			  savedFilter = filter1Repository.save(filter);
		  }
	    	 return savedFilter;
	    }
	
}
