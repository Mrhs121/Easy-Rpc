package com.hs.demo.services.impl;

import com.hs.demo.model.User;
import com.hs.demo.services.HelloService;

public class HelloServiceImpl implements HelloService {
    @Override
    public String Say(String msg) {
       return "hello "+msg;
    }

    @Override
    public String Bye(String msg) {
        return "goodbye "+msg;
    }

    @Override
    public String aaa(User user) {
        return "abc";
    }


}
