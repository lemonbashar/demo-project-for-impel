package com.demoforimpel.controller;

import com.demoforimpel.data.AccountInfo;
import com.demoforimpel.data.LoginInfo;
import com.demoforimpel.data.UserInfo;
import com.demoforimpel.domain.User;
import com.demoforimpel.security.SecurityManager;
import com.demoforimpel.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@SuppressWarnings("unchecked")
@RestController
@RequestMapping("/api")
public class AccountController {
    private final UserService userService;

    public AccountController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/account-controller/create")
    public ResponseEntity<User> createUser(@RequestBody UserInfo userInfo) {
        User user=null;
        try {
            user=userService.createUser(userInfo.getUsername(),userInfo.getPassword(),userInfo.getAuthorities().toArray(new String[0]));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return user==null?ResponseEntity.badRequest().body(null):ResponseEntity.ok(user);
    }


    @PostMapping("/account-controller/login")
    public ResponseEntity<AccountInfo> login(@RequestBody LoginInfo loginInfo) {
        return Optional.ofNullable(userService.login(loginInfo))
               .map(accountInfo -> new ResponseEntity(accountInfo, HttpStatus.OK)).orElse(new ResponseEntity(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/account-controller/jwt-test")
    public String jwtTest() {
        return SecurityManager.currentUsername();
    }
}
