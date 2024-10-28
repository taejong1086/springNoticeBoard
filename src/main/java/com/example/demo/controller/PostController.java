package com.example.demo.controller;

import com.example.demo.entity.Post;
import com.example.demo.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.List;

@Controller
public class PostController {

    @Autowired
    private PostService postService;

    @GetMapping("/posts")
    public String mainPage(Model model) {
        List<Post> posts = postService.findAllPosts();
        model.addAttribute("posts", posts);
        return "main"; // main.html로 이동
    }

    @GetMapping("/posts/create")
    public String showCreateForm(Model model) {
        model.addAttribute("post", new Post()); // 새 게시글 객체 생성
        return "create"; // create.html 반환
    }

    @PostMapping("/posts/create")
    public String createPost(@ModelAttribute Post post) {
        postService.savePost(post); // 게시글 저장
        return "redirect:/posts"; // 저장 후 목록으로 리다이렉트
    }

    @GetMapping("/posts/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        Post post = postService.findPostById(id); // ID로 게시글 조회
        model.addAttribute("post", post); // 모델에 게시글 추가
        return "edit"; // edit.html 반환
    }

    @PostMapping("/posts/edit/{id}")
    public String updatePost(@PathVariable Long id, Post post) {
        post.setId(id); // 수정할 ID 설정
        postService.savePost(post); // 게시글 저장
        return "redirect:/posts"; // 저장 후 게시글 목록으로 리다이렉트
    }
    @PostMapping("/posts/delete/{id}")
    public String deletePost(@PathVariable Long id) {
        postService.deletePost(id); // 게시글 삭제
        return "redirect:/posts"; // 삭제 후 게시글 목록으로 리다이렉트
    }

}
