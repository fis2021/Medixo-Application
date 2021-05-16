package org.loose.fis.sre.exceptions;

public class InvalidEmailException extends Exception{
    public InvalidEmailException()
    {
        super(String.format("The email address is not valid"));
    }
}
