package com.demo.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.demo.model.Product;
import com.demo.repository.ProductRepository;



@RestController
@CrossOrigin
public class ProductController {
	@Autowired
	ProductRepository productRepository;
	
	@GetMapping("/product")
	public Iterable<Product> getAllProduct(){
		return productRepository.findAll();
	}
	
	@GetMapping("/products")
	public Iterable<Product> getAllProducts(@RequestParam(name="type") String type, 
											@RequestParam(name="purity") String purity){
		return productRepository.findProducts(type,purity);
	}
	
	  @PostMapping("/products")
	    public Product createProduct(@RequestBody List<Product> productList) {
		  
		  Product savedEmployee = null;
		  for(Product product: productList) {
			  savedEmployee = productRepository.save(product);
		  }
	    	// Product savedEmployee = productRepository.save(product);
	    	 
	    	 return savedEmployee;
	    }
	  
	  @DeleteMapping("/products/{id}")
	    public String deleteProduct(@PathVariable(value = "id") Integer id)
	    {
	     Product product = productRepository.findById(id).get();

	        productRepository.delete(product);
	       
	        return "Product with "+id+" deleted successfully";
	    }

	

	
}
