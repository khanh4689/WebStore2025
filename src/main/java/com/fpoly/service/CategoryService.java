package com.fpoly.service;

import java.util.List;

import com.fpoly.entity.Category;


public interface CategoryService  {

	List<Category> findAll();
	
	List<Category> searchCategories(String keyword);

	Category findById(String id);

	Category create(Category category);

	Category update(Category category);

	void delete(String id);

}


