package com.hs.easyrpc.demo.test;

import com.hs.easyrpc.core.easyrpcclient.EasyRpcClient;
import com.hs.easyrpc.demo.model.User;
import com.hs.easyrpc.demo.model.protocol.AvroHelloWorld;
import com.hs.easyrpc.demo.model.protocol.Greeting;
import com.hs.easyrpc.demo.services.LoginService;

import java.io.IOException;
import java.net.InetSocketAddress;

public class RPCClientTest {

    public static void main(String[] args) throws IOException {
//        HelloService service = EasyRpcClient.getRemoteProxyObj(HelloService.class, new InetSocketAddress("localhost", 8088));
//        System.out.println(service.Say("huang sheng"));
//        LoginService loginservice = EasyRpcClient.getRemoteProxyObj(LoginService.class, new InetSocketAddress("localhost", 8088));
//        User user1 = new User("hs","hs");
//        for(int i =1;i<20;i++){
//            System.out.println(loginservice.login(user1));
//        }
//        System.out.println(loginservice.login(new User("hs","hs2")));
        AvroHelloWorld avroHelloWorld = EasyRpcClient.getRemoteProxyObj(AvroHelloWorld.class, new InetSocketAddress("localhost", 8088));
        avroHelloWorld.hello(new Greeting("huangsheng","123"));

        for(int i =1;i<10000;i++){
            System.out.println(avroHelloWorld.hello(new Greeting("huangsheng","123")).toString());
        }
    }
}
