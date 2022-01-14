package com.lyubchenko.springbootapplicationhomework8.repositories;

import com.lyubchenko.springbootapplicationhomework8.domain.forSecurity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UserRepositories extends JpaRepository<User, UUID> {
    User findUserByUsername(String username);
    User findUserByEmail(String email);
}
