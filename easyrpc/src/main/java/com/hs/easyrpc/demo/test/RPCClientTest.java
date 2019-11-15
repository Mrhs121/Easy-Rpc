package com.hs.easyrpc.demo.test;

import com.hs.easyrpc.core.easyrpcclient.RpcClient;
import com.hs.easyrpc.core.easyrpcclient.handler.SocketInvocationHandler;
import com.hs.easyrpc.demo.model.User;
import com.hs.easyrpc.demo.model.protocol.Greeting;
import com.hs.easyrpc.demo.services.HelloService;

import java.io.IOException;
import java.net.InetSocketAddress;

public class RPCClientTest {

    public static void main(String[] args) throws IOException {
        Greeting result = null;
//        SocketInvocationHandler socketInvocationHandler = new SocketInvocationHandler(AvroHelloWorld.class,new InetSocketAddress("localhost", 8088));
//        AvroHelloWorld avroHelloWorld = RpcClient.getProxy(AvroHelloWorld.class,socketInvocationHandler );
//        for(int i =1;i<10;i++){
//            if ( (result=avroHelloWorld.hello(new Greeting("huangsheng","123"))) !=null)
//                System.out.println(result.toString());
//        }

//        SocketInvocationHandler socketInvocationHandler1 = new SocketInvocationHandler(HelloService.class,new InetSocketAddress("localhost", 8088));
        HelloService helloService = RpcClient.getProxy(HelloService.class,new InetSocketAddress("localhost", 8088));
        for(int i =1;i<10;i++){
            System.out.println(helloService.aaa(new User("huangsheng",""+i)));
        }
//
//        SocketInvocationHandler socketInvocationHandler2 = new SocketInvocationHandler(LoginService.class,new InetSocketAddress("localhost", 8088));
//        LoginService loginService = RpcClient.getProxy(LoginService.class,socketInvocationHandler2);
//        for(int i =1;i<10;i++){
//            System.out.println(loginService.login(new User("huangsheng",""+i)));
//        }
    }


}

