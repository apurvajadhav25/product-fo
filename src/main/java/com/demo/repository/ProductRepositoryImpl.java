package com.demo.repository;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaBuilder.In;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.springframework.stereotype.Repository;
import com.demo.model.Product;

@Repository
public class ProductRepositoryImpl implements ProductRepositoryCustom {
	@PersistenceContext
	EntityManager em ;
	
	@Override
	public List<Product> findProducts(String filter1, String filter2, String price,String sort) {
		
		CriteriaBuilder cb = em.getCriteriaBuilder();
	    CriteriaQuery<Product> cq = cb.createQuery(Product.class);

	    Root<Product> product = cq.from(Product.class);
	  
	    List<Predicate> predicates = new ArrayList<>();
	    
	    if (filter1 != null) {
	    	String filter1Array[] = filter1.split(",");
	    	In<String> inClause1 = cb.in(product.get("filter1"));
			for (String title1 : filter1Array) {
			    inClause1.value(title1);
			}
	        predicates.add(inClause1);
	    }
	    
	    if (filter2 != null) {
	    	String filter2Array[] = filter2.split(",");
	    	In<String> inClause2 = cb.in(product.get("filter2"));
			for (String title2 : filter2Array) {
			    inClause2.value(title2);
			}
	        predicates.add(inClause2);
	    }
	    
	    if (price != null) {
	    	 String[] priceArray = price.split("-");
	    	 int startPrice = Integer.parseInt(priceArray[0]);
	 		 int endPrice = Integer.parseInt(priceArray[1]);
	    	 Predicate greaterThanPrice=  cb.between(product.get("price"), startPrice, endPrice);
	    	 predicates.add(greaterThanPrice);
	    }
	    
	    if(sort!=null) {
	    	if(sort=="low")
	    	  cq.orderBy(cb.asc(product.get("price")));  
	    	else
	    	  cq.orderBy(cb.desc(product.get("price")));
	    }
	     
	    cq.where(predicates.toArray(new Predicate[0]));
	    return em.createQuery(cq).getResultList();
	}

	@Override
	public List<Product> findProductsByType(String filter1) {
		
		String typeArray[] = filter1.split(",");
		CriteriaBuilder cb = em.getCriteriaBuilder(); 
		CriteriaQuery<Product> cq = cb.createQuery(Product.class);
		
		Root<Product> product = cq.from(Product.class);
		
		In<String> inClause = cb.in(product.get("filter1"));
		for (String title : typeArray) {
		    inClause.value(title);
		}
        cq.where(inClause);

        TypedQuery<Product> query = em.createQuery(cq);
        return query.getResultList();
	}

	@Override
	public List<Product> findProductsByPurity(String filter2) {
		String purityArray[] = filter2.split(",");
		
		CriteriaBuilder cb = em.getCriteriaBuilder(); CriteriaQuery<Product> cq =
		cb.createQuery(Product.class);
		  
		Root<Product> product = cq.from(Product.class);
		
		In<String> inClause = cb.in(product.get("filter2"));
		for (String title : purityArray) {
		    inClause.value(title);
		}
		 
        cq.where(inClause);

        TypedQuery<Product> query = em.createQuery(cq);
        return query.getResultList();
	}
	

}
