package com.lyubchenko.springbootapplicationhomework8.service;

import com.lyubchenko.springbootapplicationhomework8.domain.forSecurity.Authority;
import com.lyubchenko.springbootapplicationhomework8.domain.forSecurity.User;
import com.lyubchenko.springbootapplicationhomework8.repositories.AuthorityRepositories;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
@Service
public class AuthorityService implements Crud<Authority>{


    @Autowired
    private AuthorityRepositories authorityRepositories;

    @Override
    public void create(Authority entity) {

    }

    @Override
    public List<Authority> read() {
        return authorityRepositories.findAll();
    }

    @Override
    public void update(Authority entity) {

    }

    @Override
    public void deleteEntity(Authority entity) {

    }

    @Override
    public Authority getEntityById(UUID id) {
        Optional<Authority> byId = authorityRepositories.findById(id);
        return byId.orElseGet(byId::get);
    }
}
