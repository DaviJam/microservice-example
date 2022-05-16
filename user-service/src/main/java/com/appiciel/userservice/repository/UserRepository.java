package com.appiciel.userservice.repository;

import com.appiciel.userservice.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
    User findByEmail(String email);
}
