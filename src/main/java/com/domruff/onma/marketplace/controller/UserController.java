package com.domruff.onma.marketplace.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.domruff.onma.marketplace.dto.UserRequest;
import com.domruff.onma.marketplace.dto.UserResponse;
import com.domruff.onma.marketplace.service.UserService;

import lombok.RequiredArgsConstructor;

import java.util.List;

import org.springframework.http.HttpStatus;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    // Create User API Call - POST
    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public void createUser(@RequestBody UserRequest userRequest) {

        userService.createUser(userRequest);
    }

    // Login User API Call - POST
    @PostMapping("/login")
    @ResponseStatus(HttpStatus.OK)
    public UserResponse loginUser(@RequestBody UserRequest userRequest) {

        return userService.loginUser(userRequest);
    }

    // Get all Users API Call - GET
    @GetMapping("")
    @ResponseStatus(HttpStatus.OK)
    public List<UserResponse> getAllUsers() {

        return userService.getAllUsers();
    }

    // Get User by ID API Call - GET
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public UserResponse getUserById(@PathVariable Long id) {

        return userService.getUserById(id);
    }

    // Update User API Call - PUT
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void updateUser(@PathVariable Long id, @RequestBody UserRequest userRequest) {

        userService.updateUser(id, userRequest);
    }

    // Delete User API Call - DELETE
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUser(@PathVariable Long id) {

        userService.deleteUser(id);
    }

}
