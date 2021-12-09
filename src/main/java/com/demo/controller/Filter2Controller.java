package com.demo.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.demo.model.Filter2;
import com.demo.repository.Filter2Repository;

@RestController
@CrossOrigin
public class Filter2Controller {
	@Autowired
	Filter2Repository filter2Repository;
	
	@GetMapping("/filter2")
	public  Iterable<Filter2> getAllFilter2(){
		return  filter2Repository.findFilters();
	}
	
	@PostMapping("/filter2")
	public Filter2 createFilter(@RequestBody List<Filter2> filter1List) {
		  
		  Filter2 savedFilter = null;
		  for(Filter2 filter: filter1List) {
			  savedFilter = filter2Repository.save(filter);
		  }
	    	 return savedFilter;
	    }
	
}
