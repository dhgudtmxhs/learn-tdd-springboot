package com.example.tdd.service.user;

import com.example.tdd.service.user.UserService;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserServiceUnitTest {

    private final UserService userService = new UserService(null); // db 의존성 제외

    @Test
    public void testGetUserResource() {
        // given x

        // when
        String result = userService.getUserResource();

        // then
        assertEquals("Resource for user", result);
    }

    @Test
    public void testGetAdminResource() {
        // given x

        // when
        String result = userService.getAdminResource();

        // then
        assertEquals("Resource for admin", result);
    }
}