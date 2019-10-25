package com.demoforimpel.service;

import com.demoforimpel.data.AccountInfo;
import com.demoforimpel.data.LoginInfo;

public interface AuthenticationService {
    AccountInfo authenticate(LoginInfo loginInfo);

    void logout();
}
