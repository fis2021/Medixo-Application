package org.loose.fis.sre.exceptions;

public class UnavailableDayException extends Exception {

    public UnavailableDayException() {
        super(String.format("This day  is unavailable!"));
    }

}

