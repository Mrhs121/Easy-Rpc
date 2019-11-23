package com.hs.easyrpc.core.protocol;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import java.io.Serializable;
import java.util.Arrays;

// 将这个序列化替换成 avro或者protobuffer的序列化
public class RpcRequest implements Serializable{

    public String requestId;
    public String serviceName;
    public String methodName;
    Class<?>[] parameterTypes;
    Object[] parameters;


    public RpcRequest(){}

    public RpcRequest(String requestId, String serviceName, String methodName, Class<?>[] parameterTypes, Object[] parameters) {
        this.requestId = requestId;
        this.serviceName = serviceName;
        this.methodName = methodName;
        this.parameterTypes = parameterTypes;
        this.parameters = parameters;
    }

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;

    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public Class<?>[] getParameterTypes() {
        return parameterTypes;
    }

    public void setParameterTypes(Class<?>[] parameterTypes) {
        this.parameterTypes = parameterTypes;
    }

    public Object[] getParameters() {
        Object[] newarguments = new Object[this.getParameterTypes().length];
        for(int i=0;i<parameters.length;i++){
            if(parameters[i] instanceof  JSONObject) {
                Object parameter = JSON.toJavaObject((JSONObject) parameters[i], parameterTypes[i]);
                newarguments[i] = parameter;
            } else {
                newarguments[i] = parameters[i];
            }
        }
       return newarguments;
    }

    public void setParameters(Object[] parameters) {
        this.parameters = parameters;
    }

    @Override
    public String toString() {
        return "RpcRequest{" +
                "requestId='" + requestId + '\'' +
                ", serviceName='" + serviceName + '\'' +
                ", methodName='" + methodName + '\'' +
                ", parameterTypes=" + Arrays.toString(parameterTypes) +
                ", parameters=" + Arrays.toString(parameters) +
                '}';
    }

    public void print(){
        System.out.println("参数类型 ："+ parameters[0]);
//        User user = SerializeUtil.decode(parameters[0],User.class);
//        System.out.println(this.parameters[0]);
    }
}

