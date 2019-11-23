package com.hs.demo.test;

import com.hs.easyrpc.core.easyrpcserver.EasyRpcServer;
import com.hs.easyrpc.core.easyrpcserver.impl.SimpleSocketServer;
import com.hs.demo.services.HelloService;
import com.hs.demo.services.impl.HelloServiceImpl;

import java.io.IOException;

public class RPCServerTest {
    public static void main(String[] args) throws IOException {
        EasyRpcServer serviceServer = new SimpleSocketServer(8088);
        serviceServer.registerService(HelloService.class, HelloServiceImpl.class);
//        serviceServer.registerService(LoginService.class, LoginServiceImpl.class);
//        serviceServer.registerService(AvroHelloWorld.class, AvroHelloWorldImpl.class);
        serviceServer.start();
    }
}
