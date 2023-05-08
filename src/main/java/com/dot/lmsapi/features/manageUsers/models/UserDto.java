package com.dot.lmsapi.features.manageUsers.models;

import lombok.*;

@Getter
@Setter
@Data
@Builder
@ToString
public class UserDto {
    private String username;
    private String password;
    private String emailAddress;
    private String firstName;
    private String lastName;
    private String cellPhone;
}
