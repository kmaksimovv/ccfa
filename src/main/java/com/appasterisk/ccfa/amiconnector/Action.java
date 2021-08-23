package com.appasterisk.ccfa.amiconnector;

public class Action {

    public static String loginAction(String amiLogin, String amiPassword) {
        String action = "Action: Login" + "\r\n" +
                "Username: " + amiLogin + "\r\n" +
                "Secret: " + amiPassword + "\r\n\r\n";

        return action;
    }
}
