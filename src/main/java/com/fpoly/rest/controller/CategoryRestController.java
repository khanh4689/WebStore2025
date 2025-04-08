package com.fpoly.rest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fpoly.entity.Category;
import com.fpoly.service.CategoryService;

@CrossOrigin("*")
@RestController
@RequestMapping("/rest/categories")
public class CategoryRestController {
    
    @Autowired
    private CategoryService categoryService;

    // Lấy thông tin một danh mục theo ID
    @GetMapping("{id}")
    public Category getOne(@PathVariable("id") String id) {
        return categoryService.findById(id);
    }

    // Lấy danh sách tất cả các danh mục
    @GetMapping()
    public List<Category> getAll() {
        return categoryService.findAll();
    }

    // Tạo mới một danh mục
    @PostMapping()
    public Category create(@RequestBody Category category) {
        return categoryService.create(category);
    }

    // Cập nhật thông tin danh mục
    @PutMapping("{id}")
    public Category update(@RequestBody Category category, @PathVariable("id") Integer id) {
        return categoryService.update(category);
    }

    // Xóa danh mục theo ID
    @DeleteMapping("{id}")
    public void delete(@PathVariable("id") String id) {
        categoryService.delete(id);
    }
}
