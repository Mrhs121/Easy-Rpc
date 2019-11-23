package com.hs.demo.services;


import com.hs.demo.model.User;

public interface LoginService {
    public String login(User user);
    public String logout(String msg);
}
