package com.hs.easyrpc.core.easyrpcserver.impl;

import com.hs.easyrpc.core.common.CommonStrings;
import com.hs.easyrpc.core.easyrpcserver.RpcServer;
import com.hs.easyrpc.core.protocol.RpcRequest;
import com.hs.easyrpc.core.protocol.RpcResponse;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Method;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SimpleSocketServer implements RpcServer {

    private  ExecutorService executor = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

    private static final HashMap<String, Class> serviceRegistry = new HashMap<String, Class>();

    private  boolean isRunning = false;

    private  int port;

    private ServerSocket server;

    // 需要再添加一个ip地址
    public SimpleSocketServer(int port){
        this.port = port;
    }



    @Override
    public void stop() {
        try {
            this.server.close();
            isRunning = false;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void register(Class serviceInterface, Class impl) {
        // 服务注册准备写成动态的，可以动态的上线和下线服务 zk
        // 后面会将服务的信息注册在zk中
        System.out.println("注册 "+serviceInterface.getCanonicalName());
        serviceRegistry.put(serviceInterface.getName(),impl);
    }

    @Override
    public boolean isRunning() {
        return isRunning;
    }

    @Override
    public int getPort() {
        return port;
    }
    @Override
    public void start() throws  IOException{
        isRunning = true;
        server = new ServerSocket();
        server.bind(new InetSocketAddress(this.port));
        System.out.println("start server");
        try {
            while (true) {
                // 1.监听客户端的TCP连接，接到TCP连接后将其封装成task，由线程池执行
                executor.execute(new ServiceTask(server.accept()));
            }
        } finally {
            isRunning = false;
            server.close();
        }
    }
    private static class ServiceTask implements Runnable {
        Socket clent = null;

        public ServiceTask(Socket client) {
            this.clent = client;
        }

        public void run() {
            ObjectInputStream input = null;
            ObjectOutputStream output = null;
            try {

                input = new ObjectInputStream(clent.getInputStream());
                RpcRequest request = (RpcRequest)input.readObject();

                String serviceName = request.getServiceName();
                String methodName = request.getMethodName();
                Class<?>[] parameterTypes = (Class<?>[]) request.getParameterTypes();
                Object[] arguments = (Object[]) request.getParameters();



                Class serviceClass = serviceRegistry.get(serviceName);
                if (serviceClass == null) {
                    throw new ClassNotFoundException(serviceName + " not found");
                }
                Method method = serviceClass.getMethod(methodName, parameterTypes);
                Object result = method.invoke(serviceClass.newInstance(), arguments);
                RpcResponse response = new RpcResponse(request.getRequestId(), CommonStrings.RESPONSE_OK,result);

                output = new ObjectOutputStream(clent.getOutputStream());
                output.writeObject(response);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (output != null) {
                    try {
                        output.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                if (input != null) {
                    try {
                        input.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                if (clent != null) {
                    try {
                        clent.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

        }
    }

}
