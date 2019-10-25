package com.demoforimpel;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

public class UserManager {
    private static Authentication authentication() {
        return SecurityContextHolder.getContext().getAuthentication();
    }
    public static String currentUsername() {
        Object principal = authentication().getPrincipal();
        String login = null;
        if (principal instanceof UserDetails)
            login = ((UserDetails) principal).getUsername();
        else if (principal instanceof String)
            login = principal.toString();
        return login;
    }
}
