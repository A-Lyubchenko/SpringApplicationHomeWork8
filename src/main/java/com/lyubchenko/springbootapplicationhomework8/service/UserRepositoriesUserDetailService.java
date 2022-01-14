package com.lyubchenko.springbootapplicationhomework8.service;

import com.lyubchenko.springbootapplicationhomework8.domain.forSecurity.User;
import com.lyubchenko.springbootapplicationhomework8.repositories.UserRepositories;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserRepositoriesUserDetailService implements UserDetailsService {

    @Autowired
    private UserRepositories userRepositories;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User userByUsername = userRepositories.findUserByUsername(username);
        if (userByUsername!=null)
            return userByUsername;
        throw new UsernameNotFoundException("User not found");
    }
}
