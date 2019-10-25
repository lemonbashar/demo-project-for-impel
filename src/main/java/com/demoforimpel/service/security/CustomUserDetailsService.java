package com.demoforimpel.service.security;

import com.demoforimpel.data.CustomUserDetails;
import com.demoforimpel.domain.User;
import com.demoforimpel.repository.UserRepository;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class CustomUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;

    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userByUsername(username);
    }

    private UserDetails userByUsername(String username) {
        User user;
        user = (User) userRepository.findOneByUsername(username);
        if (user == null) throw new UsernameNotFoundException("User with Username :" + username + "Not Found");
        return new CustomUserDetails(user.getId(), user.getUsername(), user.getPassword(), true, user.getAuthorities().stream().map(val -> new SimpleGrantedAuthority(val.getName())).collect(Collectors.toSet()));
    }
}
