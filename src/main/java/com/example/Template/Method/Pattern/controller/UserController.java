package com.example.Template.Method.Pattern.controller;

import com.example.Template.Method.Pattern.dto.User;
import com.example.Template.Method.Pattern.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users/{id}")
    public List<User> getUser(@PathVariable String id) {
        return userService.getUser(id);
    }

}
