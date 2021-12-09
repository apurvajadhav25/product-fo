package com.demo.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.demo.model.Filter3;
import com.demo.repository.Filter3Repository;

@RestController
@CrossOrigin
public class Filter3Controller {
	@Autowired
	Filter3Repository filter3Repository;
	
	@GetMapping("/filter3")
	public  Iterable<Filter3> getAllFilter(){
		return  filter3Repository.findFilters();
	}
	
	@PostMapping("/filter3")
	public Filter3 createFilter(@RequestBody List<Filter3> filter1List) {
		  
		  Filter3 savedFilter = null;
		  for(Filter3 filter: filter1List) {
			  savedFilter = filter3Repository.save(filter);
		  }
	    	 return savedFilter;
	    }
	
}
