package com.dot.lmsapi.features.dashboard;


import com.dot.lmsapi.features.auth.services.AuthService;
import com.dot.lmsapi.features.manageUsers.models.UserResponseDto;
import com.dot.lmsapi.features.manageUsers.services.ManageUsersService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/dashboard")
public class DashboardController {
    private final ManageUsersService usersService;

    @GetMapping("/greet")
    public ResponseEntity<List<UserResponseDto>> welcome() {
        return ResponseEntity.ok(usersService.getAllUsers());
    }
}
