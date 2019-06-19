package com.hs.easyrpc.demo.test;

import com.hs.easyrpc.core.easyrpcserver.RpcServer;
import com.hs.easyrpc.core.easyrpcserver.impl.SimpleSocketServer;
import com.hs.easyrpc.demo.model.protocol.AvroHelloWorld;
import com.hs.easyrpc.demo.services.HelloService;
import com.hs.easyrpc.demo.services.LoginService;
import com.hs.easyrpc.demo.services.impl.AvroHelloWorldImpl;
import com.hs.easyrpc.demo.services.impl.HelloServiceImpl;
import com.hs.easyrpc.demo.services.impl.LoginServiceImpl;

import java.io.IOException;

public class RPCServerTest {
    public static void main(String[] args) throws IOException {
        RpcServer serviceServer = new SimpleSocketServer(8088);
        serviceServer.register(HelloService.class, HelloServiceImpl.class);
        serviceServer.register(LoginService.class, LoginServiceImpl.class);
        serviceServer.register(AvroHelloWorld.class, AvroHelloWorldImpl.class);
        serviceServer.start();
    }
}
