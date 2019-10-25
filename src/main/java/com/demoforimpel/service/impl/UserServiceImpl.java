package com.demoforimpel.service.impl;

import com.demoforimpel.data.LoginInfo;
import com.demoforimpel.domain.Authority;
import com.demoforimpel.domain.User;
import com.demoforimpel.repository.UserRepository;
import com.demoforimpel.service.AuthenticationService;
import com.demoforimpel.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private AuthenticationService authenticationService;

    @Override
    public User createUser(String username, String password,String ...authorities) {
        User user=new User();
        user.setUsername(username);
        user.setPassword(passwordEncoder.encode(password));
        Set<Authority> authoritySet=new HashSet<>();
        for(String authority:authorities)
            authoritySet.add(new Authority(authority));
        user.setAuthorities(authoritySet);
        return userRepository.save(user);
    }

    @Override
    public String login(LoginInfo loginInfo) {
        return authenticationService.authenticate(loginInfo);
    }

    @Override
    public void logout() {
        SecurityContextHolder.clearContext();
    }
}
