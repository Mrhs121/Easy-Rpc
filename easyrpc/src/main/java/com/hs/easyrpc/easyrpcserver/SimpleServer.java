package com.hs.easyrpc.easyrpcserver;

import java.io.IOException;

public interface SimpleServer {
    public void start() throws IOException;
    public void stop();
    public void register(Class serviceInterface, Class impl);
    public boolean isRunning();
    public int getPort();
}
