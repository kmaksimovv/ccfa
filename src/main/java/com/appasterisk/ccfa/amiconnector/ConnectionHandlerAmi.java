package com.appasterisk.ccfa.amiconnector;

import sun.lwawt.macosx.CPrinterDevice;
import sun.plugin2.util.SystemUtil;

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
        System.out.println(mapEvents.values().toArray().length);
    }

    private void checkLogin() {
        for (Map.Entry<String, String> entry : mapEvents.entrySet()) {
            System.out.println(entry.getKey() + " - " + entry.getValue());
        }

        System.out.println(mapEvents.get("Response"));
    }
}


