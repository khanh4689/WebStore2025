package com.fpoly.controller;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.fpoly.entity.Product;
import com.fpoly.service.ProductService;

@Controller
public class ProductController {
	@Autowired
	ProductService productService;
	@RequestMapping("/product/list")
    public String list(Model model, @RequestParam("cid") Optional<String> cid,
                       @RequestParam(defaultValue = "0") int page) {
        // Đảm bảo page không nhỏ hơn 0
        if (page < 0) {
            page = 0;
        }

        int pageSize = 8; // Số sản phẩm mỗi trang
        Pageable pageable = PageRequest.of(page, pageSize); // Tạo đối tượng Pageable

        Page<Product> productPage;

        // Kiểm tra nếu có category ID, thì lọc theo danh mục
        if (cid.isPresent()) {
            System.out.println("Loading products for category ID: " + cid.get());
            productPage = productService.findByCategoryId(cid.get(), pageable);
        } else {
            // Nếu không có category ID, tải tất cả sản phẩm
            System.out.println("Loading all products...");
            productPage = productService.findAll(pageable);
        }

        // Đảm bảo rằng list không null
        if (productPage == null) {
            System.out.println("No products found, setting empty list.");
            productPage = Page.empty(); // Trả về Page rỗng thay vì null
        } else {
            System.out.println("Total products found: " + productPage.getTotalElements());
        }

        // Lọc các phần tử null trong Page (nếu cần)
        model.addAttribute("items", productPage.getContent()); // Chuyển đổi thành list
        model.addAttribute("currentPage", page); // Lưu lại trang hiện tại
        model.addAttribute("totalPages", productPage.getTotalPages()); // Tổng số trang
        model.addAttribute("totalItems", productPage.getTotalElements()); // Tổng số sản phẩm
        model.addAttribute("cid", cid.orElse("")); // Truyền category ID cho lần tìm kiếm sau
        return "product/list";
    }

	
	@RequestMapping("/product/detail/{id}")
	public String detail(Model model, @PathVariable("id") Integer id) {
		Product item = productService.findById(id);
		model.addAttribute("item", item);
		return "product/detail";
	}
	
	@RequestMapping("/product/search")
	public String search(Model model, 
	                     @RequestParam(value = "name", required = false) String name, 
	                     @RequestParam(defaultValue = "0") int page) {
	    if (name == null || name.isEmpty()) {
	        // Nếu không có tên, có thể trả về một trang mặc định hoặc thông báo lỗi
	        model.addAttribute("error", "Vui lòng nhập tên sản phẩm để tìm kiếm.");
	        return "product/list"; // Hoặc chuyển hướng về trang khác
	    }

	    int pageSize = 5;
	    Pageable pageable = PageRequest.of(page, pageSize);
	    Page<Product> productPage = productService.searchByName(name, pageable);

	    model.addAttribute("products", productPage.getContent());
	    model.addAttribute("currentPage", page);
	    model.addAttribute("totalPages", productPage.getTotalPages());
	    model.addAttribute("totalItems", productPage.getTotalElements());
	    model.addAttribute("name", name);

	    return "product/search";
	}


}
