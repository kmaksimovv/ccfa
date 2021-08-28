package com.appasterisk.ccfa.amiconnector;

import java.io.*;
import java.net.ConnectException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketTimeoutException;

public class ConnectionHandlerAmi extends Thread {

    private static Socket clientSocket;
    private static BufferedReader inReadLine;
    private static BufferedWriter out;

    public void run() {
        try {
            createConnection();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void createConnection() throws IOException, InterruptedException {
        try {
            String readData;
            PropsAmi propsAmi = new PropsAmi();
            propsAmi.loadConfigForConnect();
            clientSocket = new Socket();
            clientSocket.connect(new InetSocketAddress(propsAmi.getHost(), propsAmi.getPort()), 2000);

            inReadLine = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            out = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));
            out.write(Action.loginAction(propsAmi.getLogin(), propsAmi.getPassword()));
            out.flush();

            while (true) {
                if (inReadLine.ready()) {
                    readData = inReadLine.readLine();
                    System.out.println(readData);
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
}


