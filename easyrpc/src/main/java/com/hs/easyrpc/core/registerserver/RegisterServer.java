package com.hs.easyrpc.core.registerserver;

import java.util.ArrayList;
import java.util.HashMap;

public interface RegisterServer {

    /**
     * 服务地址的本地缓存
     */

    /**
     * 服务注册
     * @param serviceName
     * @param ip
     * @param port
     */
    public  void register(String serviceName,String ip,int port);

    /**
     * 服务下线
     * @param serviceName
     */
    public void offline(String serviceName);

    /**
     * 服务发现
     * @return
     */
    public HashMap<String,Service> discover();
}
