package com.example.demo.service;

import com.example.demo.entity.Post;
import com.example.demo.repository.PostRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostService {

    private static final Logger logger = LoggerFactory.getLogger(PostService.class); // Logger 초기화

    @Autowired
    private PostRepository postRepository;

    public List<Post> findAllPosts() {
        try {
            List<Post> posts = postRepository.findAll(); // 모든 게시글 조회
            logger.info("Fetched posts: {}", posts); // 로그 추가
            return posts;
        } catch (Exception e) {
            logger.error("Error fetching posts", e); // 오류 로그
            throw e; // 예외를 다시 던지기
        }
    }

    public void savePost(Post post) {
        // 필드가 비어있지 않은지 확인 (선택적)
        if (post.getAuthor() == null || post.getAuthor().isEmpty()) {
            throw new IllegalArgumentException("Author cannot be null or empty");
        }
        postRepository.save(post); // 게시글 저장
    }

    public Post findPostById(Long id) {
        return postRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid post Id:" + id)); // ID로 게시글 조회
    }

    public void deletePost(Long id) {
        postRepository.deleteById(id); // ID로 게시글 삭제
    }

}
