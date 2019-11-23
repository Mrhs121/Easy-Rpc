package com.hs.demo.services.impl;

import com.hs.demo.model.User;
import com.hs.demo.services.LoginService;

public class LoginServiceImpl implements LoginService {
    @Override
    public String login(User user) {
        if (user.getName().equals(user.getPassword()))
            return user.getName()+ " YES";
        else
            return "NO";
    }

    @Override
    public String logout(String msg) {
        return "logout";
    }
}
