package com.example.demo.controller;

import com.example.demo.entity.Post;
import com.example.demo.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class MainController {

    @Autowired
    private PostService postService;

    @GetMapping("/home")
    public String adminDashboard(Model model) {
        List<Post> posts = postService.findAllPosts(); // 게시글 목록 가져오기
        model.addAttribute("posts", posts); // 모델에 추가
        return "main"; // main.html로 이동
    }
}
