package com.example.tdd.service.user;

import com.example.tdd.domain.user.User;
import com.example.tdd.repository.user.UserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class UserServiceTest {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Test
    @DisplayName("사용자 저장 및 이름으로 조회 테스트")
    void testCreateAndFindUser() {
        // Given
        String name = "ohs";
        String email = "ohs@gmail.com";
        User user = User.builder()
                .userName(name)
                .email(email)
                .build();
        userRepository.save(user); // 사용자 저장

        // When
        User foundUser = userService.findByUsername(name); // 이름으로 사용자 조회

        // Then
        assertNotNull(foundUser); // 사용자 존재 여부 확인
        assertEquals(name, foundUser.getUserName()); // 이름 확인
        assertEquals(email, foundUser.getEmail()); // 이메일 확인
    }

}
