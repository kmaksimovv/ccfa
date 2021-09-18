package com.appasterisk.ccfa.amiconnector;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.HashMap;

public class ConnectionHandlerAmi extends Thread {

    private Socket clientSocket;
    private BufferedReader inReadLine;
    private BufferedWriter out;
    private HashMap<String, String> mapEvents = new HashMap<>();

    public void run() {
        createConnection();
    }

    public void createConnection() {
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
            try {
                Thread.sleep(5000);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
            createConnection();
        } catch (Exception e) {
            System.out.println("Не удалось подключиться к серверу");
            try {
                Thread.sleep(5000);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
            createConnection();
        }
    }


    private void parseDataEventsAmi(String data) {
        if (!data.isEmpty()) {
            if (data.indexOf(':') > -1) {
                String[] dataSplit = data.split(":");
                mapEvents.put(dataSplit[0], dataSplit[1].trim());
            } else {
                mapEvents.put(data, "");
            }

        } else {
            analyze();
            mapEvents.clear();
        }
    }

    private void analyze() {
        checkLogin();

    }

    private void checkLogin() {
        if (mapEvents.containsKey("Response") &&
                mapEvents.containsValue("Success") &&
                mapEvents.containsKey("ActionID") &&
                mapEvents.containsValue("9999") &&
                mapEvents.containsKey("Message") &&
                mapEvents.containsValue("Authentication accepted")) {
            System.out.println("Successful connection");
        }
    }
}


