package com.hs.easyrpc.core.registerserver.impl;

import com.hs.easyrpc.core.registerserver.RegisterServer;
import com.hs.easyrpc.core.registerserver.Service;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;

public class SimpleRegisterServer implements RegisterServer {

    public  HashMap<String,Service> services ;

    private static SimpleRegisterServer instance = new SimpleRegisterServer();

    //让构造函数为 private，这样该类就不会被实例化
    private SimpleRegisterServer(){
        System.out.println("初始服务列表");
        services = new HashMap<>();
    }

    //获取唯一可用的对象
    public static SimpleRegisterServer getInstance(){
        return instance;
    }


    @Override
    public  void register(String serviceName, String ip, int port) {


        try {
            ObjectInputStream is = new ObjectInputStream( new FileInputStream("services.txt"));
            this.services = (HashMap<String,Service>) is.readObject();
            this.services.put(serviceName, new Service(serviceName, ip, port));
            System.out.println("注册中心收到服务：" + serviceName + " " + port);

            ObjectOutputStream os = new ObjectOutputStream(
                    new FileOutputStream("services.txt"));
            os.writeObject(services);
            os.close();
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

//        try {
//            String content = "A cat will append to the end of the file";
//            File file = new File("services.txt");
//            if (!file.exists())
//                file.createNewFile();
//            //使用true，即进行append file
//            FileWriter fileWritter = new FileWriter(file.getName(), true);
//            BufferedWriter bufferWritter = new BufferedWriter(fileWritter);
//            bufferWritter.write(serviceName+","+ip+","+port);
//            bufferWritter.close();
//            System.out.println("finish");
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
    }


    @Override
    public void offline(String serviceName) {
        this.services.remove(serviceName);
    }

    @Override
    public HashMap<String, Service> discover() {
        HashMap<String,Service> _services = new HashMap<String,Service>();
        try {
            ObjectInputStream is = new ObjectInputStream(
                    new FileInputStream("services.txt"));
//            ArrayList<StudentBean> list = new ArrayList<StudentBean>();
            _services = (HashMap<String,Service>) is.readObject();
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return _services;
    }
}
