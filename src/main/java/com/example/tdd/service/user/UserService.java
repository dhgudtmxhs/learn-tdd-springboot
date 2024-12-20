package com.example.tdd.service.user;

import com.example.tdd.domain.user.User;
import com.example.tdd.repository.user.UserRepository;
import org.springframework.stereotype.Service;

@Service
//@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User findByUsername(String name) {
        return userRepository.findByUserName(name);
    }

    // 사용자 이름 검증 로직
    public boolean isValidUserName(String userName) {
        return userName != null && userName.matches("^[a-zA-Z0-9_]{3,15}$");
    }

    // 사용자 생성 로직
    public User createUser(String userName, String email) {
        if (!isValidUserName(userName)) {
            throw new IllegalArgumentException("Invalid username");
        }
        return User.builder()
                .userName(userName)
                .email(email)
                .build();
    }

    // 일반 사용자 리소스
    public String getUserResource() {
        return "Resource for user";
    }

    // 관리자 리소스
    public String getAdminResource() {
        // return "Incorrect Resource";
        return "Resource for admin";
    }




}
