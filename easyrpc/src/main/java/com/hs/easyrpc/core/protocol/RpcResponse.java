package com.hs.easyrpc.core.protocol;

import java.io.Serializable;

public class RpcResponse implements Serializable {
    private String requestId;
    private String error;
    private Object result;

    public RpcResponse(String requestId, String error, Object result) {
        this.requestId = requestId;
        this.error = error;
        this.result = result;
    }

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public Object getResult() {
        return result;
    }

    public void setResult(Object result) {
        this.result = result;
    }
}
