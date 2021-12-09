package com.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.demo.model.Product;
import com.demo.model.Product1;
import com.demo.repository.Product1Repository;
import com.demo.repository.ProductRepository;
@RestController
@CrossOrigin
public class Product1Controller {
	
	@Autowired
	Product1Repository product1Repository;
	
	@PostMapping("/products1")
	public Product1 createProduct(@RequestBody List<Product1> productList) {
		  
		  Product1 savedEmployee = null;
		  for(Product1 product: productList) {
			  savedEmployee = product1Repository.save(product);
		  }
	    	 return savedEmployee;
	    }
	
	@GetMapping("/products1")
	public Iterable<Product1> getAllProducts(@RequestParam(name="filter1", required=false) String filter1, 
											@RequestParam(name="filter2", required=false) String filter2,
											@RequestParam(name="price", required=false) String price
											){
		
		
			return product1Repository.findAll();
		
	}

}
