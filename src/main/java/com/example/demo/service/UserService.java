package com.example.demo.service;

import com.example.demo.entity.User;
import com.example.demo.entity.UserRole;
import com.example.demo.repository.UserRepository;
import com.example.demo.repository.UserRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final UserRoleRepository userRoleRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, UserRoleRepository userRoleRepository) {
        this.userRepository = userRepository;
        this.userRoleRepository = userRoleRepository;
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    // 사용자 등록
    public void registerUser(User user, boolean isAdmin) {
        user.setRole(isAdmin ? "ROLE_ADMIN" : "ROLE_USER");
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        userRoleRepository.save(new UserRole(user.getUsername(), user.getRole()));
    }

    // 사용자 인증
    public String authenticate(String username, String password) {
        Optional<User> userOptional = userRepository.findByUsername(username);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            if (passwordEncoder.matches(password, user.getPassword())) {
                return user.getRole();
            }
        }
        return null;
    }

    // 모든 사용자 가져오기
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    // ID로 사용자 찾기
    public User getUserById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    // 사용자 정보 수정
    public void updateUser(User user) {
        // 비밀번호가 null이 아니고 빈 문자열이 아닌 경우에만 암호화
        if (user.getPassword() != null && !user.getPassword().isEmpty()) {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
        } else {
            // 비밀번호가 변경되지 않은 경우, 기존 비밀번호 유지
            Optional<User> existingUser = userRepository.findById(user.getId());
            existingUser.ifPresent(existing -> user.setPassword(existing.getPassword()));
        }
        userRepository.save(user);
    }
}
