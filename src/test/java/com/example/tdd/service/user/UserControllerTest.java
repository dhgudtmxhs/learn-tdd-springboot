package com.example.tdd.service.user;

import com.example.tdd.controller.user.UserController;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class UserControllerTest {

    private final UserService mockUserService = Mockito.mock(UserService.class);
    private final UserController userController = new UserController(mockUserService);

    @Test
    @DisplayName("Controller가 Service를 호출하는지 검증")
    public void testControllerCallsService() {
        // given
        when(mockUserService.getUserResource()).thenReturn("Mocked Resource for user");

        // when
        userController.getMockedResource();

        // then
        verify(mockUserService, times(1)).getUserResource();
    }

    @Test
    @DisplayName("Controller가 Service 결과를 올바르게 감싸는지 검증")
    public void testControllerResponseWrapping() {
        // given
        String expectedResource = "Mocked Resource for user";
        when(mockUserService.getUserResource()).thenReturn(expectedResource);

        // when
        ResponseEntity<String> response = userController.getMockedResource();

        // then
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(expectedResource, response.getBody());
    }
}
