package com.hs.easyrpc.core.easyrpcserver;

import java.io.IOException;

public interface EasyRpcServer {
    public void start() throws IOException;
    public void stop();


    /**
     * 需要参数 服务，地址，端口
     * @param serviceInterface
     * @param impl
     */
    public void registerService(Class serviceInterface, Class impl);
    public void offlineService(Class serviceInterface);
    public boolean isRunning();
    public int getPort();
}
