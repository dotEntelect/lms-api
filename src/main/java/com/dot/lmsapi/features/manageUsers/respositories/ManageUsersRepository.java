package com.dot.lmsapi.features.manageUsers.respositories;


import com.dot.lmsapi.features.manageUsers.models.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;


public interface ManageUsersRepository extends CrudRepository<User, Long> {
    Optional<User> findByEmailAddress(String email);
}
