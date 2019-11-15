package com.hs.easyrpc.demo.test;

import com.hs.easyrpc.core.easyrpcclient.EasyRpcClient;
import com.hs.easyrpc.demo.model.User;
import com.hs.easyrpc.demo.model.protocol.Greeting;
import com.hs.easyrpc.demo.services.HelloService;

import java.io.IOException;
import java.net.InetSocketAddress;

public class RPCClientTest {

    public static void main(String[] args) throws IOException {
        Greeting result = null;
//        SimpleSocketHandler socketInvocationHandler = new SimpleSocketHandler(AvroHelloWorld.class,new InetSocketAddress("localhost", 8088));
//        AvroHelloWorld avroHelloWorld = EasyRpcClient.getProxy(AvroHelloWorld.class,socketInvocationHandler );
//        for(int i =1;i<10;i++){
//            if ( (result=avroHelloWorld.hello(new Greeting("huangsheng","123"))) !=null)
//                System.out.println(result.toString());
//        }

//        SimpleSocketHandler socketInvocationHandler1 = new SimpleSocketHandler(HelloService.class,new InetSocketAddress("localhost", 8088));
        HelloService helloService = EasyRpcClient.getProxy(HelloService.class,new InetSocketAddress("localhost", 8088));
        for(int i =1;i<10;i++){
            System.out.println(helloService.aaa(new User("huangsheng",""+i)));
        }
//
//        SimpleSocketHandler socketInvocationHandler2 = new SimpleSocketHandler(LoginService.class,new InetSocketAddress("localhost", 8088));
//        LoginService loginService = EasyRpcClient.getProxy(LoginService.class,socketInvocationHandler2);
//        for(int i =1;i<10;i++){
//            System.out.println(loginService.login(new User("huangsheng",""+i)));
//        }
    }


}

