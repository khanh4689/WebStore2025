package com.fpoly.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.fpoly.entity.Category;
import com.fpoly.entity.Product;



//import com.j5.entity.Report;

public interface ProductDAO extends JpaRepository<Product, Integer> {
	@Query("SELECT p FROM Product p WHERE p.category.id=?1")
	List<Product> findByCategoryId(String cid);

	@Query("SELECT p FROM Product p WHERE p.category = :category")
	Page<Product> findByCategory(@Param("category") Category category, Pageable pageable);
	
	@Query("SELECT p FROM Product p WHERE p.name LIKE %:name%")
	Page<Product> findName(String name, Pageable pageable); 
	
	@Query("SELECT p FROM Product p WHERE LOWER(p.name) LIKE LOWER(CONCAT('%', :name, '%'))")
	Page<Product> searchByName(@Param("name") String name, Pageable pageable);
	
	Page<Product> findByCategoryId(String cid, Pageable pageable);  

	Page<Product> findAll(Pageable pageable);

    

	
}
