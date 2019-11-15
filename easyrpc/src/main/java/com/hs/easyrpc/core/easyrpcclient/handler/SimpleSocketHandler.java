package com.hs.easyrpc.core.easyrpcclient.handler;

import com.hs.easyrpc.core.common.CommonStrings;
import com.hs.easyrpc.core.protocol.RpcRequest;
import com.hs.easyrpc.core.protocol.RpcResponse;
import com.hs.easyrpc.core.utils.SerializeUtil;


import java.io.*;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.UUID;

public class SimpleSocketHandler implements InvocationHandler {

    public InetSocketAddress addr;
    public Class<?> serviceInterface;

    public SimpleSocketHandler(Class<?> serviceInterface, InetSocketAddress addr){
        this.addr = addr;
        this.serviceInterface = serviceInterface;
    }

    // 动态代理的对象，在调用任何方法的时候，都会走invoke方法
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Socket socket = null;
        ObjectOutputStream output = null;
        ObjectInputStream input = null;
        try {

            socket = new Socket();
            socket.connect(addr);

            OutputStream outputStream=socket.getOutputStream();
            output = new ObjectOutputStream(outputStream);

            RpcRequest request = new RpcRequest( UUID.randomUUID().toString(),
                                                 serviceInterface.getName(),
                                                 method.getName(),
                                                 method.getParameterTypes(),
                                                 args );
            // RpcRequest的序列化如何实现，有待考虑
//            output.writeObject(request);


            DataOutputStream dos = new DataOutputStream(outputStream);

            byte[] send = SerializeUtil.encode(request);
//            System.out.println(send.length);
            dos.write(send,0,send.length);
            dos.flush();


            // 同步阻塞等待服务器返回应答，获取应答后返回
            // 接下来这里会改为netty，异步调用 支持future、callback
            input = new ObjectInputStream(socket.getInputStream());
            RpcResponse response = (RpcResponse) input.readObject();

            if (response.getError().equals(CommonStrings.RESPONSE_OK))
                return response.getResult();
            else
                System.out.println(response.getError());
                return null;
        } finally {
            if (socket != null) socket.close();
            if (output != null) output.close();
            if (input != null) input.close();
        }
    }
}

