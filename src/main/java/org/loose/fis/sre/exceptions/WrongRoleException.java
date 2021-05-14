package org.loose.fis.sre.exceptions;

public class WrongRoleException extends Exception {
    public WrongRoleException() {
        super(String.format("Wrong role!"));
    }
}
