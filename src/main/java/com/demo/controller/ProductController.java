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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import com.demo.model.Product;
import com.demo.repository.ProductRepository;

@RestController
@CrossOrigin
public class ProductController {
	@Autowired
	ProductRepository productRepository;
	
	@GetMapping("/products")
	public Iterable<Product> getAllProducts(@RequestParam(name="filter1", required=false) String filter1, 
											@RequestParam(name="filter2", required=false) String filter2,
											@RequestParam(name="price", required=false) String price,
											@RequestParam(name="sort", required=false) String sort
											){
		
		if("".equals(filter1) && "".equals(filter2) && "".equals(price)) {
			return productRepository.findAll();
		} else if(!"".equals(filter1) && "".equals(filter2)) {
			return productRepository.findProductsByType(filter1);
		} else if("".equals(filter1) && !"".equals(filter2)) {
			return productRepository.findProductsByPurity(filter2);
		} else {
			return productRepository.findProducts(filter1,filter2,price,sort);
		}
	}
	
	@GetMapping("/sortProducts")
	public Iterable<Product> getSortedProducts(){
		return productRepository.sortProducts();
	}
	
	@GetMapping("/sortProductsDesc")
	public Iterable<Product> getSortedProductsDesc(){
		return productRepository.sortProductsByDesc();
	}

	@PostMapping("/products")
	public Product createProduct(@RequestBody List<Product> productList) {
		  
		  Product savedProduct = null;
		  for(Product product: productList) {
			  savedProduct = productRepository.save(product);
		  }
	    	 return savedProduct;
	    }
	  
	@DeleteMapping("/products/{id}")
	public String deleteProduct(@PathVariable(value = "id") Integer id)
	    {
	     Product product = productRepository.findById(id).get();
	        productRepository.delete(product);
	        return "Product with "+id+" deleted successfully";
	    }
	
	@GetMapping("/products/{id}")
	public Product getProductbyId(@PathVariable(value = "id") Integer productId) {
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
	
	

}