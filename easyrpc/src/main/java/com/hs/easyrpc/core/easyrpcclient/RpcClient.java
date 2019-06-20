package com.hs.easyrpc.core.easyrpcclient;

import java.lang.reflect.InvocationHandler;

import java.lang.reflect.Proxy;


// 结构还有待改进
public class RpcClient {

    public static <T> T getRemoteProxyObj(final Class<?> serviceInterface, InvocationHandler handler) {
        // 1.将本地的接口调用转换成JDK的动态代理，在动态代理中实现接口的远程调用
        return (T) Proxy.newProxyInstance
                (
                        serviceInterface.getClassLoader(),
                        new Class<?>[]{serviceInterface},
                        handler
               );
    }
}
