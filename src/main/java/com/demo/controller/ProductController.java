package com.demo.controller;


import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.Map.Entry;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
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
import org.springframework.web.servlet.ModelAndView;

import com.demo.model.PaytmDetailPojo;
import com.demo.model.Product;
import com.demo.repository.ProductRepository;
import com.demo.service.EmailSenderService;
import com.paytm.pg.merchant.PaytmChecksum;


@RestController
@CrossOrigin
public class ProductController {
	@Autowired
	ProductRepository productRepository;
	
	@Autowired
	private EmailSenderService service;

	@Autowired
	private PaytmDetailPojo paytmDetailPojo;
	@Autowired
	private Environment env;
	
	/*
	 * @GetMapping("/product") public Iterable<Product> getAllProduct(){ return
	 * productRepository.findAll(); }
	 */
	
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
	
	@GetMapping("/sendemail")
	public String sendmail(@RequestParam("name") String name,@RequestParam("email") String email,@RequestParam("message") String message) throws MessagingException{
		String body="Name: "+name+"\n"+
				    "Email: "+email+"\n"+
				    "Message: "+message;
		service.sendSimpleEmail(body);
		return "Email sent successfully";
	}	

	/*@GetMapping("/")
	public String home() {
		return "home";
	}

	 @GetMapping(value = "/submitPaymentDetail")
	    public ModelAndView getRedirect(@RequestParam(name = "CUST_ID") String customerId,
	                                    @RequestParam(name = "TXN_AMOUNT") String transactionAmount,
	                                    @RequestParam(name = "ORDER_ID") String orderId) throws Exception {

	        ModelAndView modelAndView = new ModelAndView("redirect:" + paytmDetailPojo.getPaytmUrl());
	        TreeMap<String, String> parameters = new TreeMap<>();
	        paytmDetailPojo.getDetails().forEach((k, v) -> parameters.put(k, v));
	        parameters.put("MOBILE_NO", env.getProperty("paytm.mobile"));
	        parameters.put("EMAIL", env.getProperty("paytm.email"));
	        parameters.put("ORDER_ID", orderId);
	        parameters.put("TXN_AMOUNT", transactionAmount);
	        parameters.put("CUST_ID", customerId);
	        String checkSum = getCheckSum(parameters);
	        parameters.put("CHECKSUMHASH", checkSum);
	        modelAndView.addAllObjects(parameters);
	        return modelAndView;
	    }
	 
	 
	 @PostMapping(value = "/pgresponse")
	    public String getResponseRedirect(HttpServletRequest request, Model model) {

	        Map<String, String[]> mapData = request.getParameterMap();
	        TreeMap<String, String> parameters = new TreeMap<String, String>();
	        String paytmChecksum = "";
	        for (Entry<String, String[]> requestParamsEntry : mapData.entrySet()) {
	            if ("CHECKSUMHASH".equalsIgnoreCase(requestParamsEntry.getKey())){
	                paytmChecksum = requestParamsEntry.getValue()[0];
	            } else {
	            	parameters.put(requestParamsEntry.getKey(), requestParamsEntry.getValue()[0]);
	            }
	        }
	        String result;

	        boolean isValideChecksum = false;
	        System.out.println("RESULT : "+parameters.toString());
	        try {
	            isValideChecksum = validateCheckSum(parameters, paytmChecksum);
	            if (isValideChecksum && parameters.containsKey("RESPCODE")) {
	                if (parameters.get("RESPCODE").equals("01")) {
	                    result = "Payment Successful";
	                } else {
	                    result = "Payment Failed";
	                }
	            } else {
	                result = "Checksum mismatched";
	            }
	        } catch (Exception e) {
	            result = e.toString();
	        }
	        model.addAttribute("result",result);
	        parameters.remove("CHECKSUMHASH");
	        model.addAttribute("parameters",parameters);
	        return "report";
	    }

	    private boolean validateCheckSum(TreeMap<String, String> parameters, String paytmChecksum) throws Exception {
	        return PaytmChecksum.verifySignature(parameters,
	                paytmDetailPojo.getMerchantKey(), paytmChecksum);
	    }


	private String getCheckSum(TreeMap<String, String> parameters) throws Exception {
		return PaytmChecksum.generateSignature(parameters, paytmDetailPojo.getMerchantKey());
	}*/
}