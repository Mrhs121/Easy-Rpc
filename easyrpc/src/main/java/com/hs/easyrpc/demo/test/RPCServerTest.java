package com.hs.easyrpc.demo.test;

import com.hs.easyrpc.core.easyrpcserver.SimpleServer;
import com.hs.easyrpc.core.easyrpcserver.impl.EasyRpcServerImpl;
import com.hs.easyrpc.demo.services.HelloService;
import com.hs.easyrpc.demo.services.LoginService;
import com.hs.easyrpc.demo.services.impl.HelloServiceImpl;
import com.hs.easyrpc.demo.services.impl.LoginServiceImpl;

import java.io.IOException;

public class RPCServerTest {
    public static void main(String[] args) throws IOException {
        SimpleServer serviceServer = new EasyRpcServerImpl(8088);
        serviceServer.register(HelloService.class, HelloServiceImpl.class);
        serviceServer.register(LoginService.class, LoginServiceImpl.class);
        serviceServer.start();
    }
}
