package com.hs.demo.services.impl;

import com.hs.demo.model.protocol.AvroHelloWorld;
import com.hs.demo.model.protocol.Greeting;
import org.apache.avro.AvroRemoteException;

public class AvroHelloWorldImpl implements AvroHelloWorld {

    @Override
    public Greeting hello(Greeting greeting) throws AvroRemoteException {
        return new Greeting("Hello my dear "+greeting.getMessage(),greeting.getPassword());
    }
}
