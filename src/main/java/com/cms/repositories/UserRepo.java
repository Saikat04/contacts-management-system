package com.cms.repositories;

import java.lang.foreign.Linker.Option;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cms.entities.User;

@Repository
public interface UserRepo extends JpaRepository<User, String> {

    //  custom query to find user by email  but we need to take care of function naming 
    Optional<User> findByEmail(String email);  

    Optional<User> findByEmailAndPassword(String email, String password);
}
