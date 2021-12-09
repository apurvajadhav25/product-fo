package com.demo.repository;

import java.util.Collection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.demo.model.Filter1;

@Repository
public interface Filter1Repository extends JpaRepository<Filter1,Integer> {
	
	 @Query("SELECT f FROM Filter1 f WHERE f.isEnable='true'")
	  Collection<Filter1> findFilters();

}
