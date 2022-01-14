package com.lyubchenko.springbootapplicationhomework8.service;

import com.lyubchenko.springbootapplicationhomework8.domain.forSecurity.Authority;
import com.lyubchenko.springbootapplicationhomework8.domain.forSecurity.User;
import com.lyubchenko.springbootapplicationhomework8.repositories.UserRepositories;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class UserService implements Crud<User> {

    @Autowired
    private UserRepositories userRepositories;


    @Override
    public void create(User entity) {
        userRepositories.save(entity);
    }

    @Override
    public List<User> read() {
        return userRepositories.findAll();

    }


    @Override
    public void update(User entity) {
        userRepositories.save(entity);
    }

    @Override
    public void deleteEntity(User entity) {
        userRepositories.delete(entity);
    }

    @Override
    public User getEntityById(UUID id) {
        Optional<User> byId = userRepositories.findById(id);
        return byId.orElseGet(byId::get);
    }

    public Optional<User> findUserByUserName(String name) {
        User userByUsername = userRepositories.findUserByUsername(name);
        return Optional.ofNullable(userByUsername);

    }

//    public Optional<User> findUserByEmail(String email) {
//        User userByEmail = userRepositories.findUserByEmail(email);
//        return Optional.ofNullable(userByEmail);
//
//    }

    public boolean userExistByName(String name) {
        return findUserByUserName(name).isPresent();
    }

    public boolean isUniqueEmail(User user) {
        User entity = userRepositories.findUserByEmail(user.getEmail());

        if (entity == null) {
            return true;
        }

        UUID userId = user.getId();
        UUID entityId = entity.getId();

        return userId.equals(entityId);
    }

    public boolean isUniqueUserName(User user) {
        User entity = userRepositories.findUserByUsername(user.getUsername());

        if (entity == null) {
            return true;
        }

        UUID userId = user.getId();
        UUID entityId = entity.getId();

        return userId.equals(entityId);
    }

}

