package com.hs.easyrpc.test;

import com.hs.easyrpc.easyrpcserver.SimpleServer;
import com.hs.easyrpc.easyrpcserver.impl.EasyRpcServerImpl;
import com.hs.easyrpc.services.HelloService;
import com.hs.easyrpc.services.LoginService;
import com.hs.easyrpc.services.impl.HelloServiceImpl;
import com.hs.easyrpc.services.impl.LoginServiceImpl;

import java.io.IOException;

public class RPCServerTest {
    public static void main(String[] args) throws IOException {
        SimpleServer serviceServer = new EasyRpcServerImpl(8088);
        serviceServer.register(HelloService.class, HelloServiceImpl.class);
        serviceServer.register(LoginService.class, LoginServiceImpl.class);
        serviceServer.start();
    }
}
