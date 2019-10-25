package com.demoforimpel.service;

import com.demoforimpel.data.AccountInfo;
import com.demoforimpel.data.LoginInfo;
import com.demoforimpel.domain.User;

public interface UserService {

    User createUser(String username,String password,String ...authorities);

    /**
     * Authenticate an user and return account-info with generated JWToken
     * @param loginInfo login information
     * @return AccountInfo
     */
    AccountInfo login(LoginInfo loginInfo);

    void logout();
}
