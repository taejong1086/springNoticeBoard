package com.example.demo.controller;

import com.example.demo.entity.User;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class AdminController {

    @Autowired
    private UserService userService;

    @GetMapping("/admin/dashboard")
    public String adminDashboard(Model model) {
        List<User> users = userService.getAllUsers(); // 모든 사용자 가져오기
        model.addAttribute("users", users);
        return "admin/dashboard"; // admin/dashboard.html 템플릿 반환
    }

    @GetMapping("/admin/users/edit/{id}")
    public String editUser(@PathVariable Long id, Model model) {
        User user = userService.getUserById(id); // ID로 사용자 찾기
        model.addAttribute("user", user);
        return "admin/editUser"; // editUser.html 템플릿 반환
    }

    @PostMapping("/admin/users/update")
    public String updateUser(User user) {
        userService.updateUser(user); // 사용자 정보 수정
        return "redirect:/admin/dashboard"; // 수정 후 대시보드로 리다이렉트
    }

    @GetMapping("/admin")
    public String adminPage(Model model) {
        return "admin"; // admin.html 템플릿 반환
    }
}
