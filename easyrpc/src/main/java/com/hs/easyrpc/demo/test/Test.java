package com.hs.easyrpc.demo.test;

import com.hs.easyrpc.core.easyrpcclient.EasyRpcClient;
import com.hs.easyrpc.demo.services.HelloService;

import java.net.InetSocketAddress;

public class Test {

    public static void main(String[] args) {
        System.out.println("Easy-Rpc First Test!");
        HelloService service = EasyRpcClient.getRemoteProxyObj(HelloService.class, new InetSocketAddress("localhost", 8088));
        for (int i=0;i<20;i++){
            System.out.println(service.Say(i+" huang sheng "));
        }
    }
}
