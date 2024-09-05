package com.domruff.onma.marketplace.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.domruff.onma.marketplace.dto.UserRequest;
import com.domruff.onma.marketplace.dto.UserResponse;
import com.domruff.onma.marketplace.model.User;
import com.domruff.onma.marketplace.repository.UserRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {

    private final UserRepository userRepository;

    // Create User
    public void createUser(UserRequest userRequest) {

        // Map request to model
        User user = User.builder()
                .name(userRequest.getName())
                .password(userRequest.getPassword())
                .build();

        // Save user to database
        userRepository.save(user);
        log.info("User {} created successfully", user.getId());
    }

    // Get all Users
    public List<UserResponse> getAllUsers() {

        List<User> users = userRepository.findAll();

        return users.stream().map(this::mapUserToUserResponse).toList();
    }

    // Get User by ID
    public UserResponse getUserById(Long id) {

        User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));

        return mapUserToUserResponse(user);
    }

    // Login User
    // Unsafe function but straightforward and enough for the scope of this project
    public UserResponse loginUser(UserRequest userRequest) {

        List<User> users = userRepository.findAll();

        User user = users.stream()
                .filter(u -> u.getName().equals(userRequest.getName())
                        && u.getPassword().equals(userRequest.getPassword()))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Login failed"));

        return mapUserToUserResponse(user);
    }

    // Update User
    public void updateUser(Long id, UserRequest userRequest) {

        User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));

        user.setName(userRequest.getName());
        user.setPassword(userRequest.getPassword());

        userRepository.save(user);
        log.info("User {} updated successfully", id);

    }

    // Delete User
    public void deleteUser(Long id) {

        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);
            log.info("User {} deleted successfully", id);
        } else {
            throw new RuntimeException("User not found");
        }
    }

    // Helper method to map Order to OrderResponse
    // Allows for Models to be hidden from the Controller
    private UserResponse mapUserToUserResponse(User user) {

        return UserResponse.builder()
                .id(user.getId())
                .name(user.getName())
                .password(user.getPassword())
                .build();
    }
}
