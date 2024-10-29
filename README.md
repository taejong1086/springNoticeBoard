# springNoticeBoard
java17, mariadb, sts

# script

```sql
create database my_database2;
```

```sql
CREATE TABLE posts (
id BIGINT AUTO_INCREMENT PRIMARY KEY,
title VARCHAR(255) NOT NULL,
content TEXT NOT NULL,
author VARCHAR(255),
created_at DATETIME DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE items (
id BIGINT AUTO_INCREMENT PRIMARY KEY,
name VARCHAR(255),
url VARCHAR(255),
post_id BIGINT NOT NULL,
FOREIGN KEY (post_id) REFERENCES posts(id) ON DELETE CASCADE
);
```
-- posts 테이블에 데이터 삽입
```sql
INSERT INTO posts (title, content, author) VALUES
('첫 번째 게시물', '이것은 첫 번째 게시물의 내용입니다.', '작성자1'),
('두 번째 게시물', '이것은 두 번째 게시물의 내용입니다.', '작성자2'),
('세 번째 게시물', '이것은 세 번째 게시물의 내용입니다.', '작성자3');

-- items 테이블에 데이터 삽입
INSERT INTO items (name, url, post_id) VALUES
('첫 번째 게시물 이미지', '/images/post1.jpg', 1),
('두 번째 게시물 이미지', '/images/post2.jpg', 2),
('세 번째 게시물 이미지', '/images/post3.jpg', 3);
```

```sql
SELECT *
from posts p ;

select *
from items i ;
```

```sql

-- 'users' 테이블 생성
CREATE TABLE users (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(255) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL,
    name VARCHAR(255),
    email VARCHAR(255) UNIQUE,
    email_id VARCHAR(255),
    last_name VARCHAR(255),
    enabled BOOLEAN DEFAULT TRUE,
    role VARCHAR(255)
);
```

```sql
-- 'user_roles' 테이블 생성
CREATE TABLE user_roles (
    username VARCHAR(255),
    role VARCHAR(255) NOT NULL,
    PRIMARY KEY (username, role),
    FOREIGN KEY (username) REFERENCES users(username) ON DELETE CASCADE
);

```
