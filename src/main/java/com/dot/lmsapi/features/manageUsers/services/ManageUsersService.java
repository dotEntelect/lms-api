package com.dot.lmsapi.features.manageUsers.services;

import com.dot.lmsapi.features.manageUsers.models.User;
import com.dot.lmsapi.features.manageUsers.models.UserResponseDto;
import com.dot.lmsapi.features.manageUsers.models.UserUpdateRequestDto;
import com.dot.lmsapi.features.manageUsers.respositories.ManageUsersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ManageUsersService {
    private final ManageUsersRepository usersRepository;

    public List<UserResponseDto> getAllUsers() {
        List<UserResponseDto> users = new ArrayList<>();
        usersRepository.findAll().forEach(user -> users.add(
                new UserResponseDto(user.getId(), user.getEmailAddress(), user.getFirstName(),
                        user.getLastName(), user.getCellPhone(), user.getRole())
        ));
        return users;
    }

    public UserResponseDto updateUser(Long id, UserUpdateRequestDto req) {
        Optional<User> user = usersRepository.findById(id);
        if (user.isPresent()) {
            user.get().setCellPhone(req.getCellPhone());
            user.get().setEmailAddress(req.getEmailAddress());
            user.get().setRole(req.getRole());
            user.get().setDateOfBirth(req.getDateOfBirth());
            user.get().setFirstName(req.getFirstName());
            user.get().setLastName(req.getLastName());
            usersRepository.save(user.get());

            return UserMapper.INSTANCE.toResponseDto(user.get());
        }
        return null;
    }
}
