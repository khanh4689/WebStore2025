package com.fpoly.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.fpoly.dao.CategoryDAO;
import com.fpoly.dao.ProductDAO;
import com.fpoly.entity.Category;
import com.fpoly.entity.Product;
import com.fpoly.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {
	@Autowired
	ProductDAO pdao;
	@Autowired
    CategoryDAO daoCategory;
	@Override
	public List<Product> findAll() {
		
		return pdao.findAll();
	}

	@Override
	public Product findById(Integer id) {
		
		return pdao.findById(id).get();
	}

	@Override
	public List<Product> findByCategoryId(String cid) {
		return pdao.findByCategoryId(cid);
	}

	@Override
    public Page<Product> findAll(Pageable pageable) {
        return pdao.findAll(pageable);
    }

	@Override
	public Page<Product> findByCategory(String id, Pageable pageable) {
		Category category = daoCategory.findById(id).get();
		Page<Product> list = pdao.findByCategory(category, pageable);
		return list;
	}

	@Override
	public Product create(Product product) {
		// TODO Auto-generated method stub
		return pdao.save(product);
	}

	@Override
	public Product update(Product product) {
		// TODO Auto-generated method stub
		return pdao.save(product);
	}

	@Override
	public void delete(Integer id) {
		pdao.deleteById(id);
	}

	@Override
	public Page<Product> findName(String name, Pageable pageable) {
		// TODO Auto-generated method stub
		return pdao.findName(name, pageable);
	}

	@Override
	public Page<Product> searchByName(String name, Pageable pageable) {
		
		return pdao.searchByName(name, pageable);
	}

	  @Override
	    public Page<Product> findByCategoryId(String cid, Pageable pageable) {
	        return pdao.findByCategoryId(cid, pageable);  // Gọi phương thức phân trang trong repository
	    }

	
}
