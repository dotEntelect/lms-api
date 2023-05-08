package com.dot.lmsapi.features.auth.models;

import com.dot.lmsapi.constants.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegistrationReq {
    private String firstName;
    private String lastName;
    private Date dateOfBirth;
    private String emailAddress;
    private String password;
    private String cellPhone;
    private Role role;
}
