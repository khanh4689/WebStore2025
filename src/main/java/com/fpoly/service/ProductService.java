package com.fpoly.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.fpoly.entity.Product;


public interface ProductService {

	  Page<Product> findAll(Pageable pageable);

	    Product findById(Integer id);
	    
	    Page<Product> findByCategory(String id, Pageable pageable);

		List<Product> findAll();

		Product create(Product product);

		Product update(Product product);

		void delete(Integer id);
		
		Page<Product> findName(String name, Pageable pageable);

		List<Product> findByCategoryId(String cid);

		Page<Product> searchByName(String name, Pageable pageable);
		
		 Page<Product> findByCategoryId(String cid, Pageable pageable);

}
