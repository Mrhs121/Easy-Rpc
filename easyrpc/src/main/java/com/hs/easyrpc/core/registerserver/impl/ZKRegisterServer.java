package com.hs.easyrpc.core.registerserver.impl;

import com.hs.easyrpc.core.registerserver.RegisterServer;
import com.hs.easyrpc.core.registerserver.Service;

import java.util.HashMap;

public class ZKRegisterServer implements RegisterServer {
    @Override
    public void register(String serviceName, String ip, int port) {

    }

    @Override
    public void offline(String serviceName) {

    }

    @Override
    public HashMap<String, Service> discover() {
        return null;
    }
}
