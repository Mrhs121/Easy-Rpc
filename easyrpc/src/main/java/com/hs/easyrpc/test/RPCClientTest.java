package com.hs.easyrpc.test;

import com.hs.easyrpc.easyrpcclient.EasyRpcClient;
import com.hs.easyrpc.model.User;
import com.hs.easyrpc.services.LoginService;

import java.io.IOException;
import java.net.InetSocketAddress;

public class RPCClientTest {

    public static void main(String[] args) throws IOException {
//        HelloService service = EasyRpcClient.getRemoteProxyObj(HelloService.class, new InetSocketAddress("localhost", 8088));
//        System.out.println(service.Say("huang sheng"));
        LoginService loginservice = EasyRpcClient.getRemoteProxyObj(LoginService.class, new InetSocketAddress("localhost", 8088));
        User user1 = new User("hs","hs");
        System.out.println(loginservice.login(user1));
//        System.out.println(loginservice.login(new User("hs","hs2")));
    }
}
