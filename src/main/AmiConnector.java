package main;

public class AmiConnector {
    String login;
    String password;
    String host;
    String port;


    public AmiConnector(String login, String password, String host, String port) {
        this.login = login;
        this.password = password;
        this.host = host;
        this.port = port;
    }

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


}
