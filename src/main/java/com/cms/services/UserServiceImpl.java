package com.cms.services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.logging.Logger;

import org.hibernate.validator.internal.util.logging.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cms.entities.User;
import com.cms.repositories.UserRepo;

@Service
public class UserServiceImpl implements UserService{    

    @Autowired
    private UserRepo userRepo;

    // private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public User saveUser(User user) {
        String userId = UUID.randomUUID().toString();
        user.setUserId(userId);
        return userRepo.save(user);
    }

    @Override
    public Optional<User> getUserById(String id) {
        return userRepo.findById(id);
    }

    @Override
    public Optional<User> updateUser(User user) {
        User exUser = userRepo.findById(user.getUserId()).orElseThrow(() -> new ResourceNotFoundException("User not found"));
        exUser.setUserId(user.getUserId());
        exUser.setName(user.getName());
        exUser.setPassword(user.getPassword());
        exUser.setEmail(user.getEmail());
        exUser.setContactNumber(user.getContactNumber());
        exUser.setAbout(user.getAbout());
        exUser.setProfileLink(user.getProfileLink());
        exUser.setEnabled(user.isEnabled());
        exUser.setEmailVarified(user.isEmailVarified());
        exUser.setPhoneVarified(user.isPhoneVarified());
        exUser.setProvider(user.getProvider());
        exUser.setProviderUserId(user.getProviderUserId());
        userRepo.save(exUser);
        // save user
        User save = userRepo.save(exUser);
        return Optional.ofNullable(save);
    }

    @Override
    public Optional<User> deleteUser(String id) {
        User exUser = userRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("User not found"));
        userRepo.delete(exUser);
        return Optional.ofNullable(exUser);
    }

    @Override
    public boolean isUserExists(String id) {
        User exUser = userRepo.findById(id).orElse(null);
        return exUser != null;
    }

    @Override
    public boolean isUserExistsByEmail(String email) {
        return userRepo.findByEmail(email).isPresent();
    }

    @Override
    public List<User> getAllUsers() {
        return userRepo.findAll();
    }

}
