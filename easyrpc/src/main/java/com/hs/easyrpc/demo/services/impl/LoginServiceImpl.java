package com.hs.easyrpc.demo.services.impl;

import com.hs.easyrpc.demo.model.User;
import com.hs.easyrpc.demo.services.LoginService;

public class LoginServiceImpl implements LoginService {
    @Override
    public String login(User user) {
        if (user.getName().equals(user.getPassword()))
            return "YES";
        else
            return "NO";
    }
}
