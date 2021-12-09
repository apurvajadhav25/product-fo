package com.demo.repository;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.demo.model.Image;

@Repository
public interface ImageRepository extends JpaRepository<Image, Integer>{
	
	@Query("SELECT i FROM Image i WHERE i.product.id= :id")
	 Collection<Image> findByImages(@Param("id") int id);

}
