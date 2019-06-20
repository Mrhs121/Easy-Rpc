package com.hs.easyrpc.demo.test;

import com.alibaba.fastjson.JSON;
import com.hs.easyrpc.core.protocol.RpcRequest;
import com.hs.easyrpc.core.utils.SerializeUtil;
import com.hs.easyrpc.demo.model.protocol.AvroHelloWorld;

public class Test {

    public static void main(String[] args) {
//        System.out.println("Easy-Rpc First Test!");
//        HelloService service = RpcClient.getRemoteProxyObj(HelloService.class, new InetSocketAddress("localhost", 8088));
//        for (int i=0;i<20;i++){
//            System.out.println(service.Say(i+" huang sheng "));
//        }
        Class[] classss= {
            AvroHelloWorld.class
        };

        Object[] objects = {new String("hs"),1};
        RpcRequest request = new RpcRequest("a","a","a",classss,objects);
        byte[] a= SerializeUtil.encode(request);
        System.out.println(a.length);
        RpcRequest b =  SerializeUtil.decodeByte(a,RpcRequest.class);
        System.out.println(b.toString());
        //        String json = JSON.toJSONString(request);
//        System.out.println(json);
//        RpcRequest q = JSON.parseObject(json,RpcRequest.class);
//        System.out.println(q.toString());

    }
}
