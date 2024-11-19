package com.example.tdd.service.user;

import com.example.tdd.domain.user.User;
import com.example.tdd.repository.user.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

// Mock 객체 : 행위 검증(behavior verification)
// Stub 객체 : 상태 검증(state verification)

// Mock 객체는 메서드 호출 여부와 호출된 방식(횟수, 인자 등)을 검증하는 데 중점을 둔다.
// Stub 객체는 특정 상황에서 어떤 데이터를 반환하거나 상태를 검증하는 데 초점을 둔다.
// 둘다 Mockito 사용함
public class UserServiceMockStubTest {

    private UserRepository userRepository;

    private UserService userService;

    @BeforeEach
    void setUp() {
        userRepository = mock(UserRepository.class); // Mockito로 가짜 저장소 생성
        userService = new UserService(userRepository); // 가짜 저장소를 서비스에 주입
    }

    // Mock Test
    @Test
    void testFindByUserNameMock() {
        // Given
        String name = "ohs";
        User mockUser = User.builder()
                .userName(name)
                .email("ohs@gmail.com")
                .build();

        // Mock 동작
        when(userRepository.findByUserName(name)).thenReturn(mockUser);

        // When
        userService.findByUsername(name);

        // Then
        verify(userRepository, times(1)).findByUserName(name); // 메서드 호출 여부 및 횟수 검증
    }

    // Stub Test
    @Test
    void testFindByUserNameStub() {
        // Given
        String name = "ohs";
        String email = "ohs@gmail.com";
        User stubUser = User.builder()
                .userName(name)
                .email(email)
                .build();

        // Stub 동작
        when(userRepository.findByUserName(name)).thenReturn(stubUser);

        // When
        User foundUser = userService.findByUsername(name); // 서비스 호출

        // Then
        assertEquals(name, foundUser.getUserName()); // 반환된 데이터 검증
        assertEquals(email, foundUser.getEmail());   // 반환된 데이터 검증
    }
}
