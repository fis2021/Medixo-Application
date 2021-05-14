package org.loose.fis.sre.model;

public class WhoIsLoggedInfo {
    public static String loggedUsername;
    public static String loggedName;

    public static String getLoggedUsername() {
        return loggedUsername;
    }

    public static String getLoggedName() {
        return loggedName;
    }

    public static void setLoggedUsername(String loggedUsername) {
        WhoIsLoggedInfo.loggedUsername = loggedUsername;
    }

    public static void setLoggedName(String loggedName) {
        WhoIsLoggedInfo.loggedName = loggedName;
    }
}