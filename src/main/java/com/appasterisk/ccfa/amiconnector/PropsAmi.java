package com.appasterisk.ccfa.amiconnector;

import java.io.*;
import java.util.Properties;

public class PropsAmi {

    public static final String PATH_TO_PROPERTIES = "src/main/resources/config.properties";

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

            fileInputStream = new FileInputStream(PATH_TO_PROPERTIES);
            properties.load(fileInputStream);

            login = properties.getProperty("ami.login");
            password = properties.getProperty("ami.password");
            host = properties.getProperty("ami.host");
            port = properties.getProperty("ami.port");

        } catch (IOException e) {
            System.out.println("Ошибка! Файл " + PATH_TO_PROPERTIES + " не найден!");
            e.printStackTrace();
        } finally {
            if (fileInputStream != null) {
                fileInputStream.close();
            }
        }

        return properties;
    }
}
