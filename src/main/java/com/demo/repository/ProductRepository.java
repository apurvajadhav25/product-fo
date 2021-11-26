package com.demo.repository;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.demo.model.Product;
@Repository
public interface ProductRepository extends JpaRepository<Product, Integer>{
	@Query("SELECT u FROM Product u WHERE u.type=:type or u.purity= :purity")
	Collection<Product> findProducts(@Param("type") String type,@Param("purity") String purity);

	@Query("SELECT u FROM Product u WHERE u.type=:type")
	Collection<Product> findProducts(@Param("type") String type);
  //@Query("SELECT u FROM Product u WHERE u.type =  :type")
 //Collection<Product> findProductAccToType(@Param("type") String type);
	@Query("SELECT u FROM Product u WHERE u.type=:purity")
	Collection<Product> findProducts1(@Param("purity") String purity);

}
