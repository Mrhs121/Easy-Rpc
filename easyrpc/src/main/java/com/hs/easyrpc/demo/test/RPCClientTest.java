package com.hs.easyrpc.demo.test;

import com.hs.easyrpc.core.easyrpcclient.EasyRpcClient;
import com.hs.easyrpc.core.easyrpcclient.handler.SocketInvocationHandler;
import com.hs.easyrpc.demo.model.User;
import com.hs.easyrpc.demo.model.protocol.AvroHelloWorld;
import com.hs.easyrpc.demo.model.protocol.Greeting;
import com.hs.easyrpc.demo.services.HelloService;
import com.hs.easyrpc.demo.services.LoginService;

import java.io.IOException;
import java.net.InetSocketAddress;

public class RPCClientTest {

    public static void main(String[] args) throws IOException {

        SocketInvocationHandler socketInvocationHandler = new SocketInvocationHandler(AvroHelloWorld.class,new InetSocketAddress("localhost", 8088));
        AvroHelloWorld avroHelloWorld = EasyRpcClient.getRemoteProxyObj(AvroHelloWorld.class,socketInvocationHandler );
        for(int i =1;i<10;i++){
            System.out.println(avroHelloWorld.hello(new Greeting("huangsheng","123")).toString());
        }

        SocketInvocationHandler socketInvocationHandler1 = new SocketInvocationHandler(HelloService.class,new InetSocketAddress("localhost", 8088));
        HelloService helloService = EasyRpcClient.getRemoteProxyObj(HelloService.class,socketInvocationHandler1);
        for(int i =1;i<10;i++){
            System.out.println(helloService.Say("huangsheng"));
        }

        SocketInvocationHandler socketInvocationHandler2 = new SocketInvocationHandler(LoginService.class,new InetSocketAddress("localhost", 8088));
        LoginService loginService = EasyRpcClient.getRemoteProxyObj(LoginService.class,socketInvocationHandler2);
        for(int i =1;i<10;i++){
            System.out.println(loginService.login(new User("huangsheng",""+i)));
        }
    }
}
