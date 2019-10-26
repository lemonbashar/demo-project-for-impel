package com.demoforimpel.config.security;

import com.demoforimpel.data.CustomUserDetails;
import com.demoforimpel.data.LoginInfo;
import com.demoforimpel.data.props.ApplicationProperties;
import com.demoforimpel.domain.TokenStore;
import com.demoforimpel.domain.User;
import com.demoforimpel.repository.TokenStoreRepository;
import com.demoforimpel.security.UserManager;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.annotation.PostConstruct;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.stream.Collectors;

@Component
public class TokenProvider {
    private static final String AUTHORITIES_KEY = "auth";
    private static final String USER_ID_KEY = "uid";
    private Key key;
    private long tokenValidityInMilliseconds;
    private long tokenValidityInMillisecondsForRememberMe;

    private final ApplicationProperties applicationProperties;
    private final TokenStoreRepository tokenStoreRepository;

    public TokenProvider(ApplicationProperties applicationProperties, TokenStoreRepository tokenStoreRepository) {
        this.applicationProperties = applicationProperties;
        this.tokenStoreRepository = tokenStoreRepository;
    }

    @PostConstruct
    public void init() {
        byte[] keyBytes;
        String secret = applicationProperties.getJwt().getSecret();
        if (!StringUtils.isEmpty(secret)) {
            keyBytes = secret.getBytes(StandardCharsets.UTF_8);
        } else {
            keyBytes = Decoders.BASE64.decode(applicationProperties.getJwt().getBase64Secret());
        }
        this.key = Keys.hmacShaKeyFor(keyBytes);
        this.tokenValidityInMilliseconds =
                1000 * applicationProperties.getJwt().getTokenValidityInSeconds();
        this.tokenValidityInMillisecondsForRememberMe =
                1000 * applicationProperties.getJwt().getTokenValidityInSecondsForRememberMe();
    }

    public String createToken(Authentication authentication, LoginInfo loginInfo) {
        String authorities = authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(","));

        long now = (new Date()).getTime();
        Date validity;
        if (loginInfo.isRememberMe()) {
            validity = new Date(now + this.tokenValidityInMillisecondsForRememberMe);
        } else {
            validity = new Date(now + this.tokenValidityInMilliseconds);
        }
        CustomUserDetails customUserDetails= (CustomUserDetails) authentication.getPrincipal();

        String token= Jwts.builder()
                .setSubject(authentication.getName())
                .claim(AUTHORITIES_KEY, authorities)
                .claim(USER_ID_KEY, customUserDetails.getId().toString())
                .signWith(key, SignatureAlgorithm.HS512)
                .setExpiration(validity)
                .compact();
        tokenStoreRepository.save(new TokenStore(token, new User(customUserDetails.getId()), validity, true));

        return token;
    }

    Authentication getAuthentication(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(key)
                .parseClaimsJws(token)
                .getBody();

        Collection<? extends GrantedAuthority> authorities =
                Arrays.stream(claims.get(AUTHORITIES_KEY).toString().split(","))
                        .map(SimpleGrantedAuthority::new)
                        .collect(Collectors.toList());

        Long uid = Long.parseLong(claims.get(USER_ID_KEY).toString());
        UserDetails principal = new CustomUserDetails(uid, claims.getSubject(), "", true, new HashSet<>(authorities));

        return new UsernamePasswordAuthenticationToken(principal, token, authorities);
    }

    boolean validateToken(String authToken) {
        TokenStore tokenStore=tokenStoreRepository.findByToken(authToken);
        return tokenStore!=null && tokenStore.getActive() && parseValidate(authToken);
    }


    private boolean parseValidate(String authToken) {
        try {
            Jwts.parser().setSigningKey(key).parseClaimsJws(authToken);
            return true;
        } catch (io.jsonwebtoken.security.SecurityException | MalformedJwtException e) {
            System.err.println("Invalid JWT signature trace: "+e.getMessage());
        } catch (ExpiredJwtException e) {
            System.err.println("Expired JWT token trace: "+e.getMessage());
        } catch (UnsupportedJwtException e) {
            System.err.println("Unsupported JWT token trace:"+e.getMessage());
        } catch (IllegalArgumentException e) {
            System.err.println("JWT token compact of handler are invalid trace:"+ e.getMessage());
        }
        return false;
    }

    public void logout() {
        tokenStoreRepository.deactivateAll(UserManager.currentUserId());
        SecurityContextHolder.clearContext();
    }
}

