package com.appasterisk.ccfa;


import com.appasterisk.ccfa.amiconnector.ConnectionHandlerAmi;

import java.io.IOException;

public class Application {
    public static void main(String[] args) throws IOException {
        ConnectionHandlerAmi connectionHandlerAmi = new ConnectionHandlerAmi();
        connectionHandlerAmi.start();

    }
}
