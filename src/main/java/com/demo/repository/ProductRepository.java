package com.demo.repository;

import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.demo.model.Product;
//@Repository
public interface ProductRepository extends JpaRepository<Product, Integer>, ProductRepositoryCustom{
	
	/*
	 * @Query("SELECT p FROM Product p WHERE p.type=:type and p.purity= :purity")
	 * Collection<Product> findProducts(@Param("type") String type,@Param("purity")
	 * String purity);
	 * 
	 * @Query("SELECT p FROM Product p WHERE p.type=:type") Collection<Product>
	 * findProductsByType(@Param("type") String type);
	 * 
	 * @Query("SELECT p FROM Product p WHERE p.purity= :purity") Collection<Product>
	 * findProductsByPurity(@Param("purity") String purity);
	 */
	
	   @Query("SELECT p FROM Product p  ORDER BY p.price ")
	   Collection<Product> sortProducts();
	   
	   @Query("SELECT p FROM Product p  ORDER BY p.price Desc")
	   Collection<Product> sortProductsByDesc();
	  
}
