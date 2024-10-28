package com.example.demo.repository;

import com.example.demo.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository // Spring의 Component 스캔에 의해 빈으로 등록
public interface PostRepository extends JpaRepository<Post, Long> {
    // 기본적인 CRUD 메서드는 JpaRepository가 제공하므로, 추가적인 메서드가 필요한 경우 여기에서 정의
}
