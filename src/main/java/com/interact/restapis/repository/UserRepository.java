package com.interact.restapis.repository;

import com.interact.restapis.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    User getUserByEmail(String email);
//    Optional<User> findByUsername(String email);
    Optional<User> findByEmail(String email);
}
