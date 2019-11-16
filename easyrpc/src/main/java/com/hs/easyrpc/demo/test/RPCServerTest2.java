package com.hs.easyrpc.demo.test;

import com.hs.easyrpc.core.easyrpcserver.EasyRpcServer;
import com.hs.easyrpc.core.easyrpcserver.impl.SimpleSocketServer;
import com.hs.easyrpc.demo.model.protocol.AvroHelloWorld;
import com.hs.easyrpc.demo.services.HelloService;
import com.hs.easyrpc.demo.services.LoginService;
import com.hs.easyrpc.demo.services.impl.AvroHelloWorldImpl;
import com.hs.easyrpc.demo.services.impl.HelloServiceImpl;
import com.hs.easyrpc.demo.services.impl.LoginServiceImpl;

import java.io.IOException;

public class RPCServerTest2 {
    public static void main(String[] args) throws IOException {
        EasyRpcServer serviceServer = new SimpleSocketServer(8080);
//        serviceServer.registerService(HelloService.class, HelloServiceImpl.class);
        serviceServer.registerService(LoginService.class, LoginServiceImpl.class);
//        serviceServer.registerService(AvroHelloWorld.class, AvroHelloWorldImpl.class);
        serviceServer.start();
    }
}
