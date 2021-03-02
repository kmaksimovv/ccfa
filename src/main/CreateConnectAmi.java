package main;

import java.io.*;
import java.util.Properties;

public class CreateConnectAmi {

    public void connectionAmi() throws IOException {
        FileInputStream fileInputStream = null;
        Properties properties = new Properties();
        String fileName = "config.properties";

        try {
            fileInputStream = new FileInputStream(fileName);
            properties.load(fileInputStream);
        } catch (FileNotFoundException e){

        }
    }
}
