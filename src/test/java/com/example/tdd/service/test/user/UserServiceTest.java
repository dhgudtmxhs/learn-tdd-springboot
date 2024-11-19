package com.example.tdd.service.test.user;

import com.example.tdd.domain.user.User;
import com.example.tdd.repository.user.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

// Mock 객체 : 행위 검증(behavior verification)
// Stub을 객체 : 상태 검증(state verification)

// Mock 객체는 메서드 호출 여부와 호출된 방식(횟수, 인자 등)을 검증하는 데 중점을 둔다.
// Stub 객체는 특정 상황에서 어떤 데이터를 반환하거나 상태를 유지하는 데 초점을 둔다.
public class UserServiceTest {

    private UserRepository userRepository;

    private UserService userService;

    @BeforeEach
    void setUp() { // 테스트 마다 객체 초기화
        userRepository = mock(UserRepository.class); // Mock Repository 생성
        userService = new UserService(userRepository); // Service에 주입
    }

    //Mock Test
    @Test
    void testFindByUserName() {
        // Given
        String name = "ohs";
        User mockUser = User.builder().
                        userName(name).
                        email("ohs@gmail.com").
                        build();

        // Mock 동작 정의
        when(userRepository.findByUserName(name)).thenReturn(mockUser);

        // When
        User foundUser = userService.findByUsername(name);

        // Then
        assertEquals(name, foundUser.getUserName()); // 결과 검증
        verify(userRepository, times(1)).findByUserName(name); // findByUserName 호출 검증
    }

}
