package com.demo.repository;

import java.util.List;

import com.demo.model.Product;

public interface ProductRepositoryCustom {
	List<Product> findProducts(String filter1,String filter2,String price,String sort);
	List<Product> findProductsByType(String filter1);
	List<Product> findProductsByPurity(String filter2);

}
