package com.example.tdd.controller.user;

import com.example.tdd.service.user.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/resource")
    public ResponseEntity<String> getResource() {
        return ResponseEntity.ok("Resource for user");
    }

    @GetMapping("/admin")
    public ResponseEntity<String> getAdminResource() {
        return ResponseEntity.ok("Admin resource");
    }

    @GetMapping("/mocked-resource/user")
    public ResponseEntity<String> getMockedResource() {
        return ResponseEntity.ok(userService.getUserResource());
    }

}
