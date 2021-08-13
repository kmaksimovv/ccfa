package com.appasterisk.ccfa;


import com.appasterisk.ccfa.amiconnector.ConnectorAmi;

import java.io.IOException;

public class Application {
    public static void main(String[] args) throws IOException {
        ConnectorAmi connectorAmi = new ConnectorAmi();
        connectorAmi.createConnection();
    }
}
