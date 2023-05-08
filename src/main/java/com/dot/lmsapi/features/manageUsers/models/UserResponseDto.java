package com.dot.lmsapi.features.manageUsers.models;

import com.dot.lmsapi.constants.Role;

public record UserResponseDto(Long id, String emailAddress, String firstName, String lastName,
                              String cellPhone, Role role) {
}
