package com.demoforimpel.service;

import com.demoforimpel.data.LoginInfo;

public interface AuthenticationService {
    String authenticate(LoginInfo loginInfo);
}
