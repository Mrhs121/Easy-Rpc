package com.hs.easyrpc.core.easyrpcserver.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.hs.easyrpc.core.common.CommonStrings;
import com.hs.easyrpc.core.easyrpcserver.EasyRpcServer;
import com.hs.easyrpc.core.protocol.RpcRequest;
import com.hs.easyrpc.core.protocol.RpcResponse;
import com.hs.easyrpc.core.utils.SerializeUtil;

import java.io.*;
import java.lang.reflect.Method;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SimpleSocketServer implements EasyRpcServer {

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
        Socket client = null;

        public ServiceTask(Socket client) {
            this.client = client;
        }

        public void run() {
            ObjectInputStream input = null;
            ObjectOutputStream output = null;
            RpcResponse response = null;
            try {

//                input = new ObjectInputStream(client.getInputStream());
//                RpcRequest request = (RpcRequest)input.readObject();
                InputStream inputStream= this.client.getInputStream();//
                input = new ObjectInputStream(inputStream);

                DataInputStream dis = new DataInputStream(inputStream);

                byte[] data = new byte[1024];
//                System.out.println(data.length);
                int size = dis.read(data);
//                byte[] data2 =  new byte[1024];
//                System.arraycopy(data,4,data2,0,size);
//                System.out.println(data2.length);
                RpcRequest request = SerializeUtil.decodeByte(data,RpcRequest.class);


                String serviceName = request.getServiceName();
                String methodName = request.getMethodName();
                System.out.println("length : "+request.getParameterTypes().length+" "+request.getParameters().length);
                System.out.println("call "+serviceName+" --> "+methodName);
                Class<?>[] parameterTypes = request.getParameterTypes();
                for(Class c :parameterTypes){
                    System.out.println("arg type："+c);
                }
                Object[] arguments = request.getParameters();
                for (Object o : arguments){
                    System.out.println("obj : "+o.getClass()+ "data : "+o);
                }

//                Object[] newarguments = new Object[request.getParameterTypes().length];
//                for(int i=0;i<arguments.length;i++){
//                    if(arguments[i] instanceof  JSONObject) {
//                        Object parameter = JSON.toJavaObject((JSONObject) arguments[i], parameterTypes[i]);
//                        newarguments[i] = parameter;
//                    } else {
//                        newarguments[i] = arguments[i];
//                    }
//                }

                Class serviceClass = serviceRegistry.get(serviceName);
                if (serviceClass == null) {
                    response = new RpcResponse(request.getRequestId(), serviceName + " not found",null);
                    output = new ObjectOutputStream(client.getOutputStream());
                    output.writeObject(response);
                    throw new ClassNotFoundException(serviceName + " not found");
                }
                Method method = serviceClass.getMethod(methodName, parameterTypes);
                System.out.println("method : "+method);

                Object result = method.invoke(serviceClass.newInstance(), arguments);

                response = new RpcResponse(request.getRequestId(), CommonStrings.RESPONSE_OK,result);
                //System.out.println(response.toString());
                output = new ObjectOutputStream(client.getOutputStream());
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
                if (client != null) {
                    try {
                        client.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

        }
    }

}
