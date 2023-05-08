package com.dot.lmsapi.features.auth.services;

import com.dot.lmsapi.features.auth.models.*;
import com.dot.lmsapi.features.manageUsers.models.User;
import com.dot.lmsapi.features.manageUsers.models.UserDto;
import com.dot.lmsapi.features.manageUsers.models.UserResponseDto;
import com.dot.lmsapi.features.manageUsers.respositories.ManageUsersRepository;
import com.dot.lmsapi.config.JwtService;
import com.dot.lmsapi.constants.Role;
import com.dot.lmsapi.features.manageUsers.services.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final ManageUsersRepository manageUsersRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;


    public Long saveDto(UserDto userDto) {
        userDto.setPassword(passwordEncoder.encode(userDto.getPassword()));
        var user = manageUsersRepository.save(UserMapper.INSTANCE.fromDto(userDto));
        return user.getId();
    }

    public AuthResponse register(RegistrationReq req, boolean generateToken) {

        User user = User.builder()
                .firstName(req.getFirstName())
                .lastName(req.getLastName())
                .dateOfBirth(req.getDateOfBirth())
                .cellPhone(req.getCellPhone())
                .emailAddress(req.getEmailAddress())
                .role(req.getRole()) // new users have no permissions in the system
                .enabled(true) // and this should be false by default
                .password(passwordEncoder.encode(req.getPassword()))
                .build();

        var savedUser = manageUsersRepository.save(user);

        if (generateToken) {
            String jwtToken = jwtService.generateToken(savedUser);
            return AuthResponse
                    .builder()
                    .token(jwtToken)
                    .userDetails(new UserResponseDto(savedUser.getId(), savedUser.getEmailAddress(), savedUser.getFirstName(),
                            savedUser.getLastName(), savedUser.getCellPhone(), savedUser.getRole()))
                    .build();
        }

        return AuthResponse
                .builder()
                .userDetails(new UserResponseDto(savedUser.getId(), savedUser.getEmailAddress(), savedUser.getFirstName(),
                        savedUser.getLastName(), savedUser.getCellPhone(), savedUser.getRole()))
                .build();
    }

    public AuthResponse login(LoginReq req) {

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        req.getEmailAddress(),
                        req.getPassword()
                )
        );
        var user = manageUsersRepository.findByEmailAddress(req.getEmailAddress()).orElseThrow();
        System.out.println(user);
        String jwtToken = jwtService.generateToken(user);

        return AuthResponse
                .builder()
                .token(jwtToken)
                .userDetails(new UserResponseDto(user.getId(), user.getEmailAddress(), user.getFirstName(),
                        user.getLastName(), user.getCellPhone(), user.getRole()))
                .build();
    }

    public Optional<User> getLoggedInUser() {
        var context = (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext()
                .getAuthentication();
        User user = (User) context.getPrincipal();
        return Optional.of(user);
    }
}
