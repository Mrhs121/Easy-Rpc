package com.hs.easyrpc.services.impl;

import com.hs.easyrpc.model.User;
import com.hs.easyrpc.services.LoginService;

public class LoginServiceImpl implements LoginService {
    @Override
    public String login(User user) {
        if (user.getName().equals(user.getPassword()))
            return "YES";
        else
            return "NO";
    }
}
