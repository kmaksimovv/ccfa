package com.appasterisk.ccfa.amiconnector;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.HashMap;
import java.util.Map;

public class ConnectionHandlerAmi extends Thread {

    private Socket clientSocket;
    private BufferedReader inReadLine;
    private BufferedWriter out;
    private HashMap<String, String> mapEvents = new HashMap<>();

    public void run() {
        try {
            createConnection();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void createConnection() throws IOException {
        try {
            PropsAmi propsAmi = new PropsAmi();
            propsAmi.loadConfigForConnect();
            clientSocket = new Socket();
            clientSocket.connect(new InetSocketAddress(propsAmi.getHost(), propsAmi.getPort()), 2000);
            inReadLine = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            out = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));
            out.write(Action.loginAction(propsAmi.getLogin(), propsAmi.getPassword()));
            out.flush();

            String data;
            while (true) {
                if (inReadLine.ready()) {
                    data = inReadLine.readLine();
                    parseDataEventsAmi(data);
                }
            }
        } catch (SocketTimeoutException e) {
            System.out.println("Ошибка сокет тайм-аут");
            createConnection();
        } catch (Exception e) {
            System.out.println("Не удалось подключиться к серверу");
            createConnection();
        }
    }

    private void parseDataEventsAmi(String data) {

        if (data.contains(":")) {
            String[] dataSplit = data.split(":");
            mapEvents.put(dataSplit[0], dataSplit[1]);

            for (Map.Entry<String, String> entry : mapEvents.entrySet()) {
                System.out.println("key: " + entry.getKey() + ", " + "value: " + entry.getValue());
            }

        }
    }

    private void checkLogin() {

    }
}


