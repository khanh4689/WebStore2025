package com.fpoly.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.fpoly.entity.Account;
import com.fpoly.entity.Authority;
import com.fpoly.service.AccountService;
import com.fpoly.service.AuthorityService;
import com.fpoly.service.RoleService;

@Controller
@RequestMapping("register")
public class RegisterController {

    @Autowired
    AccountService accountService;

    @Autowired
    AuthorityService authorityService;

    @Autowired
    RoleService roleService;

    @Autowired
    PasswordEncoder passwordEncoder;

    // Hiển thị form đăng ký
    @GetMapping
    public String showForm() {
        return "/security/register";
    }

    // Xử lý đăng ký
    @PostMapping
    public String processRegister(
            @RequestParam("fullname") String fullname,
            @RequestParam("email") String email,
            @RequestParam("username") String username,
            @RequestParam("password") String password,
            @RequestParam("confirmPassword") String confirmPassword,
            RedirectAttributes redirectAttributes,
            Model model) {

        // Kiểm tra xác nhận mật khẩu
        if (!password.equals(confirmPassword)) {
            model.addAttribute("message", "Mật khẩu xác nhận không trùng khớp!");
            return "/security/register";
        }

        // Kiểm tra trùng username
        if (accountService.findByUsername(username).isPresent()) {
            model.addAttribute("message", "Tên đăng nhập đã tồn tại!");
            return "/security/register";
        }

        // Kiểm tra trùng email
        if (accountService.findByEmail(email).isPresent()) {
            model.addAttribute("message", "Email đã được sử dụng!");
            return "/security/register";
        }

        // Tạo tài khoản mới
        Account account = new Account();
        account.setFullname(fullname);
        account.setEmail(email);
        account.setUsername(username);
        account.setPassword(passwordEncoder.encode(password));
        account.setPhoto("user.png");
        accountService.create(account);

        // Gán quyền mặc định
        Authority authority = new Authority();
        authority.setAccount(account);
        authority.setRole(roleService.findById("CUST")); // Giả sử vai trò mặc định là "CUST"
        authorityService.create(authority);

        redirectAttributes.addFlashAttribute("message", "Đăng ký thành công! Bạn có thể đăng nhập.");
        return "redirect:/login/sign-in";
    }
}
