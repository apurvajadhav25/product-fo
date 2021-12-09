package com.demo.repository;

import java.util.Collection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.demo.model.Filter1;
import com.demo.model.Filter3;

@Repository
public interface Filter3Repository extends JpaRepository<Filter3,Integer> {
	
	 @Query("SELECT f FROM Filter3 f WHERE f.isEnable='true'")
	  Iterable<Filter3> findFilters();

}
