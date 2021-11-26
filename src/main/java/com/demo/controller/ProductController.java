package com.demo.controller;


import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import javax.mail.MessagingException;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import com.demo.model.Product;
import com.demo.repository.ProductRepository;
import com.demo.service.EmailSenderService;


@RestController
@CrossOrigin
public class ProductController {
	@Autowired
	ProductRepository productRepository;
	
	@Autowired
	private EmailSenderService service;
	
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
	    	 return savedEmployee;
	    }
	  
	@DeleteMapping("/products/{id}")
	public String deleteProduct(@PathVariable(value = "id") Integer id)
	    {
	     Product product = productRepository.findById(id).get();

	        productRepository.delete(product);
	       
	        return "Product with "+id+" deleted successfully";
	    }
	
	@GetMapping("/products/{id}")
	public Product getProductbyId(@PathVariable(value = "id") Integer productId)
      
	{
		Product product = productRepository.findById(productId).get();
		
		return product;	
	}
	
	@PostMapping("/uploadFile")
	public ResponseEntity<Object> fileUpload(@RequestParam("file") MultipartFile file) throws IOException{
	File myFile = new File("D:\\storage\\"+file.getOriginalFilename());
		myFile.createNewFile();
		FileOutputStream fos =new FileOutputStream(myFile);
		fos.write(file.getBytes());
		fos.close();
		return new ResponseEntity<Object>("The File Uploaded Successfully", HttpStatus.OK);
	}
	
	@PostMapping("/sendemail")
	public String sendmail(@RequestBody() String to) throws MessagingException{
		
		service.sendSimpleEmail(to,"Welcome to our store","Grettings from tanishq");
		return "Email sent successfully";
	}	

}