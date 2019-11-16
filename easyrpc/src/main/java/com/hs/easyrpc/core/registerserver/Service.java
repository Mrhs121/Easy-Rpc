package com.hs.easyrpc.core.registerserver;

import java.io.Serializable;

public class Service implements Serializable {
    String sevicename;
    String ip;
    int port;

    public String getSevicename() {
        return sevicename;
    }

    public void setSevicename(String sevicename) {
        this.sevicename = sevicename;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public Service(){}

    public Service(String sevicename, String ip, int port) {

        this.sevicename = sevicename;
        this.ip = ip;
        this.port = port;
    }

    @Override
    public String toString() {
        return "Service{" +
                "sevicename='" + sevicename + '\'' +
                ", ip='" + ip + '\'' +
                ", port='" + port + '\'' +
                '}';
    }
}
