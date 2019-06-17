package com.hs.easyrpc.test;

import com.hs.easyrpc.client.SimpleRpcClinet;
import com.hs.easyrpc.server.SimpleServer;
import com.hs.easyrpc.server.impl.SimpleServerImpl;
import com.hs.easyrpc.services.HelloService;
import com.hs.easyrpc.services.impl.HelloServiceImpl;

import java.io.IOException;
import java.net.InetSocketAddress;

public class RPCTest {

    public static void main(String[] args) throws IOException {
        new Thread(new Runnable() {
            public void run() {
                try {
                    SimpleServer serviceServer = new SimpleServerImpl(8088);
                    serviceServer.register(HelloService.class, HelloServiceImpl.class);
                    serviceServer.start();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
        HelloService service = SimpleRpcClinet.getRemoteProxyObj(HelloService.class, new InetSocketAddress("localhost", 8088));
        System.out.println(service.Say("huang sheng"));
    }
}
