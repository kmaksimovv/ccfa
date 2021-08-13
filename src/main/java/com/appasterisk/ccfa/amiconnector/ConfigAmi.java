package com.appasterisk.ccfa.amiconnector;

import java.io.*;
import java.util.Properties;

public class ConfigAmi {

    private String login;
    private String password;
    private String host;
    private String port;

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public String getHost() {
        return host;
    }

    public String getPort() {
        return port;
    }

    public Properties loadConfigForConnect() throws IOException {

        FileInputStream fileInputStream = null;
        Properties properties = new Properties();
        try {

            String fileName = "src/main/resources/config.properties";
            fileInputStream = new FileInputStream(fileName);
            properties.load(fileInputStream);

            login = properties.getProperty("ami.login");
            password = properties.getProperty("ami.password");
            host = properties.getProperty("ami.host");
            port = properties.getProperty("ami.port");

        } catch (FileNotFoundException e) {
            System.out.println("Ошибка! Файл config.properties не найден!");
        } finally {
            if (fileInputStream != null) {
                fileInputStream.close();
            }
        }

        return properties;
    }
}
