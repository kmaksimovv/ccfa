package com.appasterisk.ccfa.amiconnector;

public class Action {

    public static String loginAction(String amiLogin, String amiPassword) {

        return "Action: Login" + "\r\n" +
                "ActionId: " + 9999 + "\r\n" +
                "Username: " + amiLogin + "\r\n" +
                "Secret: " + amiPassword + "\r\n\r\n";
    }
}
