package com.dot.lmsapi.features.auth.controllers;

import com.dot.lmsapi.features.auth.models.AuthResponse;
import com.dot.lmsapi.features.auth.models.LoginReq;
import com.dot.lmsapi.features.auth.models.RegistrationReq;
import com.dot.lmsapi.features.auth.services.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<AuthResponse> saveDto(@RequestBody RegistrationReq req) {
        // register user
        return ResponseEntity.ok(authService.register(req, true));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> saveDto(@RequestBody LoginReq req) {
        // authenticate user
        return ResponseEntity.ok(authService.login(req));
    }
  }
