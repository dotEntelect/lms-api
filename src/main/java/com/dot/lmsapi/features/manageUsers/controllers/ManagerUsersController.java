package com.dot.lmsapi.features.manageUsers.controllers;

import com.dot.lmsapi.features.manageUsers.models.UserResponseDto;
import com.dot.lmsapi.features.manageUsers.models.UserUpdateRequestDto;
import com.dot.lmsapi.features.manageUsers.services.ManageUsersService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/manage-users")
public class ManagerUsersController {
    private final ManageUsersService usersService;

    @GetMapping
    public ResponseEntity<List<UserResponseDto>> getAllUsers() {
        return ResponseEntity.ok(usersService.getAllUsers());
    }
    @PutMapping("{id}")
    public ResponseEntity<UserResponseDto> updateUser(@PathVariable("id") Long id, @RequestBody UserUpdateRequestDto req) {
        UserResponseDto user = usersService.updateUser(id, req);
        return ResponseEntity.ok(user);
    }
}
