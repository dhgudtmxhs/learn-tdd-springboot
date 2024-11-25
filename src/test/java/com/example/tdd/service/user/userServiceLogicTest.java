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
        assertAll(
                () -> {
                    // Given
                    String invalidUserName = "a";

                    // When
                    boolean result = userService.isValidUserName(invalidUserName);

                    // Then
                    assertFalse(result, "너무짧은 userName");
                },
                () -> {
                    // Given
                    String invalidUserName = null;

                    // When
                    boolean result = userService.isValidUserName(invalidUserName);

                    // Then
                    assertFalse(result, "userName에 Null 불가능");
                },
                () -> {
                    // Given
                    String invalidUserName = "invalid!@#";

                    // When
                    boolean result = userService.isValidUserName(invalidUserName);

                    // Then
                    assertFalse(result, "userName에 특수문자 불가능");
                }
        );
    }

    @Test
    @DisplayName("유효한 사용자 생성")
    void testCreateUser_Valid() {
        // Given
        String validUserName = "validUser";
        String email = "validUser@example.com";

        // When
        User user = userService.createUser(validUserName, email);

        // Then
        assertNotNull(user, "User는 null일 수 없음");
        assertEquals(validUserName, user.getUserName(), "UserName이 일치해야 함");
        assertEquals(email, user.getEmail(), "Eamil이 일치해야 함");
    }

    @Test
    @DisplayName("잘못된 사용자 생성 - 예외 발생")
    void testCreateUser_Invalid() {
        // Given
        String invalidUserName = "invalid@@USER!!";
        String email = "email@example.com";

        // When
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            userService.createUser(invalidUserName, email);
        });

        // Then
        assertEquals("Invalid username", exception.getMessage(), "예외 메시지가 예상과 일치하는지 확인");
    }

}