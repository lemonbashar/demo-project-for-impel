package com.demoforimpel.service;

import com.demoforimpel.data.LoginInfo;
import com.demoforimpel.domain.User;

public interface UserService {

    User createUser(String username,String password,String ...authorities);

    /**
     * Authenticate an user and return generated JWToken
     * @param loginInfo login information
     * @return JWToken
     */
    String login(LoginInfo loginInfo);

    void logout();
}
