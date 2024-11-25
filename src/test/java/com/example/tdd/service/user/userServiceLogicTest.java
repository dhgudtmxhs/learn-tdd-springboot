package com.example.tdd.service.user;

import com.example.tdd.domain.user.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


// db연결 없는 서비스 로직만 테스트
public class userServiceLogicTest {

    private UserService userService;

    @BeforeEach
    void SetUp() {
        userService = new UserService(null); // repo 의존성 제외
    }

    @Test
    @DisplayName("사용자 이름 유효성 검증 - 성공")
    void testIsValidUserName_Valid() {
        //assertTrue(userService.isValidUserName("validUser123"));

        // Given
        String vaildUserName = "validUser123";

        // When
        boolean isValid = userService.isValidUserName(vaildUserName);

        // Then
        assertTrue(isValid);
    }

    @Test
    @DisplayName("사용자 이름 유효성 검증 - 실패")
    void testIsValidUserName_Invalid() {
        assertFalse(userService.isValidUserName("a")); // 짧음
        assertFalse(userService.isValidUserName(null)); // null
        assertFalse(userService.isValidUserName("invalid!@#")); // 특수 문자 포함
    }

    @Test
    @DisplayName("유효한 사용자 생성")
    void testCreateUser_Valid() {
        User user = userService.createUser("validUser", "validUser@example.com");
        assertNotNull(user);
        assertEquals("validUser", user.getUserName());
        assertEquals("validUser@example.com", user.getEmail());
    }

    @Test
    @DisplayName("잘못된 사용자 생성 - 예외 발생")
    void testCreateUser_Invalid() {
        assertThrows(IllegalArgumentException.class, () -> {
            userService.createUser("invalid@@USER!!", "email@example.com");
        });
    }


}
