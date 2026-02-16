package com.example.user_profile_api_26970.controller.response;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.user_profile_api_26970.model.response.ApiResponse;
import com.example.user_profile_api_26970.model.response.UserProfile;

@RestController
@RequestMapping("/api/users")
public class UserProfileController {

    private List<UserProfile> users = new ArrayList<>();

    // GET ALL
    @GetMapping
    public ApiResponse<List<UserProfile>> getAllUsers() {
        return new ApiResponse<>("All users retrieved successfully", users);
    }

    // GET BY ID
    @GetMapping("/{userId}")
    public ApiResponse<UserProfile> getUserById(@PathVariable Long userId) {
        UserProfile user = users.stream()
                .filter(u -> u.getUserId().equals(userId))
                .findFirst()
                .orElse(null);

        return new ApiResponse<>("User retrieved", user);
    }

    // CREATE
    @PostMapping
    public ApiResponse<UserProfile> createUser(@RequestBody UserProfile user) {
        users.add(user);
        return new ApiResponse<>("User created successfully", user);
    }

    // UPDATE
    @PutMapping("/{userId}")
    public ApiResponse<UserProfile> updateUser(@PathVariable Long userId,
                                               @RequestBody UserProfile updatedUser) {

        for (UserProfile user : users) {
            if (user.getUserId().equals(userId)) {
                user.setUsername(updatedUser.getUsername());
                user.setEmail(updatedUser.getEmail());
                user.setFullName(updatedUser.getFullName());
                user.setAge(updatedUser.getAge());
                user.setCountry(updatedUser.getCountry());
                user.setBio(updatedUser.getBio());
                user.setActive(updatedUser.isActive());

                return new ApiResponse<>("User updated successfully", user);
            }
        }

        return new ApiResponse<>("User not found", null);
    }

    // DELETE
    @DeleteMapping("/{userId}")
    public ApiResponse<String> deleteUser(@PathVariable Long userId) {
        users.removeIf(u -> u.getUserId().equals(userId));
        return new ApiResponse<>("User deleted successfully", null);
    }

    // SEARCH BY USERNAME
    @GetMapping("/search")
    public ApiResponse<List<UserProfile>> searchByUsername(@RequestParam String username) {

        List<UserProfile> result = users.stream()
                .filter(u -> u.getUsername().equalsIgnoreCase(username))
                .collect(Collectors.toList());

        return new ApiResponse<>("Search result", result);
    }

    // SEARCH BY COUNTRY
    @GetMapping("/country/{country}")
    public ApiResponse<List<UserProfile>> getByCountry(@PathVariable String country) {

        List<UserProfile> result = users.stream()
                .filter(u -> u.getCountry().equalsIgnoreCase(country))
                .collect(Collectors.toList());

        return new ApiResponse<>("Users from " + country, result);
    }

    // SEARCH BY AGE RANGE
    @GetMapping("/age")
    public ApiResponse<List<UserProfile>> getByAgeRange(@RequestParam int min,
                                                        @RequestParam int max) {

        List<UserProfile> result = users.stream()
                .filter(u -> u.getAge() >= min && u.getAge() <= max)
                .collect(Collectors.toList());

        return new ApiResponse<>("Users between ages " + min + " and " + max, result);
    }

    // ACTIVATE
    @PatchMapping("/{userId}/activate")
    public ApiResponse<String> activateUser(@PathVariable Long userId) {
        for (UserProfile user : users) {
            if (user.getUserId().equals(userId)) {
                user.setActive(true);
                return new ApiResponse<>("User activated", null);
            }
        }
        return new ApiResponse<>("User not found", null);
    }

    // DEACTIVATE
    @PatchMapping("/{userId}/deactivate")
    public ApiResponse<String> deactivateUser(@PathVariable Long userId) {
        for (UserProfile user : users) {
            if (user.getUserId().equals(userId)) {
                user.setActive(false);
                return new ApiResponse<>("User deactivated", null);
            }
        }
        return new ApiResponse<>("User not found", null);
    }
}

