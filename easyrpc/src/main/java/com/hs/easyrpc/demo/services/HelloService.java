package com.hs.easyrpc.demo.services;

import com.hs.easyrpc.demo.model.User;

import java.security.PublicKey;

public interface HelloService {
    public String Say(String msg);
    public String Bye(String msg);
    public String aaa(User user);
}
