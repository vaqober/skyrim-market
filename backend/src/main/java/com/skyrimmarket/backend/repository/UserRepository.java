package com.skyrimmarket.backend.repository;

import com.skyrimmarket.backend.model.user.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Long> {

    Optional<User> getUserByUsernameAndPassword(String username, String password);
}