package com.hs.easyrpc.core.utils;


import com.alibaba.fastjson.JSON;
import org.apache.avro.data.Json;

import java.lang.reflect.Type;

public class SerializeUtil {

    public static byte[] encode(Object data){
        return JSON.toJSONBytes(data);

    }

    // 学到了
    public static  <T> T decodeByte(byte[] data, Type type){
        return JSON.parseObject(data,type);
    }

    // 学到了
    public static  <T> T decodeString(String data, Type type){
        return JSON.parseObject(data,type);
    }
}
