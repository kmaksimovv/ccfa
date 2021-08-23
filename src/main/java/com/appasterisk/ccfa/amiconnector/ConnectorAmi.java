package com.appasterisk.ccfa.amiconnector;

import java.io.*;
import java.net.Socket;
import java.util.Arrays;
import java.util.Map;

import static java.util.stream.Collectors.toMap;

public class ConnectorAmi {

    private static Socket clientSocket;
    private static BufferedReader in;
    private static BufferedWriter out;

    public void createConnection() throws IOException {
        PropsAmi propsAmi = new PropsAmi();
        propsAmi.loadConfigForConnect();

        clientSocket = new Socket(propsAmi.getHost(), Integer.parseInt(propsAmi.getPort()));

        in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        out = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));
        out.write(Action.loginAction(propsAmi.getLogin(), propsAmi.getPassword()));
        out.flush();
        String read;
        while((read=in.readLine())!=null){
            System.out.println(read);
        }
    }
}


