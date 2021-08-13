package com.appasterisk.ccfa.amiconnector;

import java.io.*;
import java.net.Socket;

public class ConnectorAmi {
  ConfigAmi configAmi;

    private static Socket clientSocket;
    private static BufferedReader in;
    private static BufferedWriter out;

    public void createConnection() throws IOException {
        configAmi = new ConfigAmi();
        configAmi.loadConfigForConnect();

        clientSocket = new Socket(configAmi.getHost(), Integer.parseInt(configAmi.getPort()));

        in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        out = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));
        out.write(AsteriskAmiAction.loginAction(configAmi.getLogin(), configAmi.getPassword())); // отправляем сообщение на сервер
        out.flush();
        String serverWord = in.readLine(); // ждём, что скажет сервер
        System.out.println(serverWord); // получив - выводим на экран
    }
}


