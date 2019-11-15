package com.hs.easyrpc.core.easyrpcserver;

import java.io.IOException;

public interface EasyRpcServer {
    public void start() throws IOException;
    public void stop();
    public void register(Class serviceInterface, Class impl);
    public boolean isRunning();
    public int getPort();
}
