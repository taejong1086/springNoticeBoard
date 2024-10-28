package com.example.demo.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "items")
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String url;  // 예: 이미지 또는 파일의 경로

    // Post와의 관계 매핑 (ManyToOne 관계)
    @ManyToOne
    @JoinColumn(name = "post_id", nullable = false)
    private Post post;

    // 기본 생성자
    public Item() {}

    // 생성자
    public Item(String name, String url) {
        this.name = name;
        this.url = url;
    }

    // Getter 및 Setter 메서드
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }
}
