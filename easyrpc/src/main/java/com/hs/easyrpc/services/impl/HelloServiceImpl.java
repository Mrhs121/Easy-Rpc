package com.hs.easyrpc.services.impl;

import com.hs.easyrpc.services.HelloService;

public class HelloServiceImpl implements HelloService {
    @Override
    public String Say(String msg) {
       return "hello "+msg;
    }

    @Override
    public String Bye(String msg) {
        return "goodbye "+msg;
    }
}
