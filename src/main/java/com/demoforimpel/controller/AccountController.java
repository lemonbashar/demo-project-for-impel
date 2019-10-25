package com.demoforimpel.controller;

import com.demoforimpel.data.UserInfo;
import com.demoforimpel.domain.User;
import com.demoforimpel.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class AccountController {
    @Autowired
    private UserService userService;

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
}
