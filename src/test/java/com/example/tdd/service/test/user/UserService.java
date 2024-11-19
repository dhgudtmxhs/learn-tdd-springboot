package com.example.tdd.service.test.user;

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

}
