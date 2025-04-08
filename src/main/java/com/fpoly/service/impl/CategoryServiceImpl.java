package com.fpoly.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fpoly.dao.CategoryDAO;
import com.fpoly.entity.Category;
import com.fpoly.service.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryDAO cdao;

    @Override
    public List<Category> findAll() {
        // Trả về danh sách tất cả các danh mục
        return cdao.findAll();
    }

    @Override
    public List<Category> searchCategories(String keyword) {
        // Tìm kiếm các danh mục theo tên (hoặc các tiêu chí khác)
        return cdao.searchByName(keyword);
    }

    @Override
    public Category findById(String id) {
        // Tìm danh mục theo ID
        return cdao.findById(id).orElse(null); // Nếu không tìm thấy, trả về null
    }

    @Override
    public Category create(Category category) {
        // Tạo mới một danh mục
        return cdao.save(category);
    }

    @Override
    public Category update(Category category) {
        // Kiểm tra xem danh mục có tồn tại trước khi cập nhật không
        if (category != null && category.getId() != null) {
            return cdao.save(category); // Cập nhật danh mục
        }
        return null; // Trả về null nếu không tìm thấy danh mục để cập nhật
    }

    @Override
    public void delete(String id) {
        // Kiểm tra xem danh mục có tồn tại trước khi xóa không
        if (id != null) {
            cdao.deleteById(id); // Xóa danh mục theo ID
        }
    }

	
}
