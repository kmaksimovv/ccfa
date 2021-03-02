package main;

import java.io.*;
import java.util.Properties;

public class CreateConnectAmi {

    public Properties connectionAmi() throws IOException {

        FileInputStream fileInputStream = null;
        Properties properties = new Properties();
        try {

            String fileName = "config.properties";
            fileInputStream = new FileInputStream(fileName);
            properties.load(fileInputStream);
            parseProperties(properties);

        } catch (FileNotFoundException e) {
            System.out.println("Ошибка! Файл config.properties не найден!");
        } finally {
            if (fileInputStream != null) {
                fileInputStream.close();
            }
        }

        return properties;
    }

    public void parseProperties(Properties properties) {
        String login = properties.getProperty("ami.login");
        String password = properties.getProperty("ami.password");
        String host = properties.getProperty("ami.host");
        String port = properties.getProperty("ami.port");

        System.out.println("login: " + login + "\r\n" + "password: " + password + "\r\n" + "host: " + host + "\r\n" + "port: " + port + "\r\n");
    }
}
