package com.dot.lmsapi.features.auth.models;

import com.dot.lmsapi.features.manageUsers.models.UserResponseDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthResponse {
    private String token;
    private UserResponseDto userDetails;
}
