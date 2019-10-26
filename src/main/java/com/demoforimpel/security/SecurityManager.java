package com.demoforimpel.security;

import com.demoforimpel.data.CustomUserDetails;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

public class SecurityManager {
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

    public static Long currentUserId() {
        Authentication authentication = authentication();
        if (authentication.getPrincipal() instanceof CustomUserDetails)
            return ((CustomUserDetails) authentication.getPrincipal()).getId();
        throw new SecurityException("Not a Valid Authentication to Find User-Id");
    }
}
