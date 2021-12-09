package com.demo.repository;

import java.util.Collection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.demo.model.Filter2;

@Repository
public interface Filter2Repository extends JpaRepository<Filter2,Integer> {
	
	 @Query("SELECT f FROM Filter2 f WHERE f.isEnable='true'")
	  Collection<Filter2> findFilters();

}
