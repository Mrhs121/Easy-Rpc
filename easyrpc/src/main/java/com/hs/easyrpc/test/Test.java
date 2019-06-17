package com.hs.easyrpc.test;

import com.hs.easyrpc.client.SimpleRpcClinet;
import com.hs.easyrpc.services.HelloService;

import java.net.InetSocketAddress;

public class Test {
    public static void main(String[] args) {
        System.out.println("Easy-Rpc First Test!");
        HelloService service = SimpleRpcClinet.getRemoteProxyObj(HelloService.class, new InetSocketAddress("localhost", 8088));
        for (int i=0;i<20;i++){
            System.out.println(service.Say(i+" huang sheng "));
        }
    }
}
