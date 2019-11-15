package com.hs.easyrpc.core.easyrpcclient;

import com.hs.easyrpc.core.easyrpcclient.handler.SimpleSocketHandler;

import java.lang.reflect.Proxy;
import java.net.InetSocketAddress;


// 结构还有待改进
public class EasyRpcClient {

    public static <T> T getProxy(final Class<?> serviceInterface, InetSocketAddress inetSocketAddress) {
        // 1.将本地的接口调用转换成JDK的动态代理，在动态代理中实现接口的远程调用
        return (T) Proxy.newProxyInstance
                (
                        serviceInterface.getClassLoader(),
                        new Class<?>[]{serviceInterface},
                        new SimpleSocketHandler(serviceInterface, inetSocketAddress)
               );
    }
}
