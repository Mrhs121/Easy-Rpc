package com.hs.easyrpc.core.easyrpcclient.handler;

import com.hs.easyrpc.core.protocol.RpcRequest;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.UUID;

public class SocketInvocationHandler implements InvocationHandler {

    public InetSocketAddress addr;
    public Class<?> serviceInterface;

    public SocketInvocationHandler(Class<?> serviceInterface,InetSocketAddress addr){
        this.addr = addr;
        this.serviceInterface = serviceInterface;
    }

    // 动态代理的对象，在调用任何方法的时候，都会走invoke方法
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Socket socket = null;
        ObjectOutputStream output = null;
        ObjectInputStream input = null;
        try {
            // 2.创建Socket客户端，根据指定地址连接远程服务提供者
            socket = new Socket();
            socket.connect(addr);

            // 3.将远程服务调用所需的接口类、方法名、参数列表等编码后发送给服务提供者
            output = new ObjectOutputStream(socket.getOutputStream());
            RpcRequest request = new RpcRequest( UUID.randomUUID().toString(),
                                                 serviceInterface.getName(),
                                                 method.getName(),
                                                 method.getParameterTypes(),
                                                 args );
            output.writeObject(request);

            // 4.同步阻塞等待服务器返回应答，获取应答后返回
            input = new ObjectInputStream(socket.getInputStream());
            return input.readObject();
        } finally {
            if (socket != null) socket.close();
            if (output != null) output.close();
            if (input != null) input.close();
        }
    }
}

