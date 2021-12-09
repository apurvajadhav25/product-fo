package com.demo.repository;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.demo.model.Filter1;
import com.demo.model.Product1;

public interface Product1Repository extends JpaRepository<Product1, Integer>{
	
	 @Query("SELECT p FROM Product1 p WHERE p.id in (id)")
	  Collection<Filter1> findFilters();

}
