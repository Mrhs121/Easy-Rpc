package com.hs.easyrpc.core.easyrpcclient;

import com.hs.easyrpc.core.easyrpcclient.handler.SimpleSocketHandler;
import com.hs.easyrpc.core.registerserver.RegisterServer;
import com.hs.easyrpc.core.registerserver.Service;
import com.hs.easyrpc.core.registerserver.impl.SimpleRegisterServer;

import java.lang.reflect.Proxy;
import java.net.InetSocketAddress;
import java.util.HashMap;


// 结构还有待改进
public class EasyRpcClient {

    public static HashMap<String, Service> services = new HashMap<>();

    public static RegisterServer simpleServer = SimpleRegisterServer.getInstance();

    public static void discoverServer(){
        // 周期性的向 服务注册中心 请求最新的service地址
        services.clear();
        services = simpleServer.discover();
    }

    public  static  <T> T getProxy(final Class<?> serviceInterface) {
        // 1.将本地的接口调用转换成JDK的动态代理，在动态代理中实现接口的远程调用
        // 这里 首先需要向服务注册中心 询问服务的地址，服务注册中心通过网络返回 list（map（servicename，ip：port））
        // 客户端定时向服务中心请求最新的服务列表


        discoverServer();

        return (T) Proxy.newProxyInstance
                (
                        serviceInterface.getClassLoader(),
                        new Class<?>[]{serviceInterface},
                        new SimpleSocketHandler(serviceInterface,
                                new InetSocketAddress(services.get(serviceInterface.getName()).getIp(),services.get(serviceInterface.getName()).getPort())
                        )
               );
    }

    // 需要在本地备份一下 service列表 ，在服务端注册的服务（所有服务的格数都是一样的）可能存在各种地方，不一定要在当前机器上
}
