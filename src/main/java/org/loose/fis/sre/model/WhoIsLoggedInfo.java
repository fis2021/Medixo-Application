package org.loose.fis.sre.model;

public class WhoIsLoggedInfo {
    public static String loggedUsername;

    public static String getLoggedUsername() {
        return loggedUsername;
    }

    public static void setLoggedUsername(String loggedUsername) {
        WhoIsLoggedInfo.loggedUsername = loggedUsername;
    }
}