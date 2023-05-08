package com.dot.lmsapi.features.manageUsers.models;

import com.dot.lmsapi.constants.Role;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.Date;

@RequiredArgsConstructor
@Data
public class UserUpdateRequestDto {
    private Long id;
    private String emailAddress;
    private String firstName;
    private String lastName;
    private String cellPhone;
    private Date dateOfBirth;
    private Role role;
}
