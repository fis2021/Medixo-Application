package org.loose.fis.sre.exceptions;

public class EmptyTextfieldsException extends Exception{
    public EmptyTextfieldsException() {
        super(String.format("Cannot be empty!"));
    }
}