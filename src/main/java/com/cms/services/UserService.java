package com.cms.services;

import java.lang.foreign.Linker.Option;
import java.util.List;
import java.util.Optional;

import com.cms.entities.User;

public interface UserService {
    User saveUser(User user);
    Optional<User> getUserById(String id);
    Optional<User> updateUser(User user);
    Optional<User> deleteUser(String id);
    boolean isUserExists(String id);
    boolean isUserExistsByEmail(String email);
    List<User> getAllUsers();
    User getUserByEmail(String email);
}
