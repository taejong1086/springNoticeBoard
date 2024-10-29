package com.example.demo.controller;

import com.example.demo.entity.User;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AuthController {

    @Autowired
    private UserService userService;

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new User());
        return "register";
    }

    @PostMapping("/register")
    public String registerUser(@RequestParam String username, @RequestParam String password, @RequestParam(required = false) boolean admin) {
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        userService.registerUser(user, admin);
        return "redirect:/login";
    }

    @GetMapping("/login")
    public String showLoginForm() {
        return "login";
    }

    @PostMapping("/login")
    public String login(@RequestParam String username, @RequestParam String password, Model model) {
        String role = userService.authenticate(username, password);

        if (role != null) {
            if (role.equals("ROLE_ADMIN")) {
                return "redirect:/admin/dashboard";
            } else {
                return "redirect:/posts";
            }
        } else {
            model.addAttribute("error", "잘못된 사용자 이름 또는 비밀번호입니다.");
            return "login";
        }
    }
}
