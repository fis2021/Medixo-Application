package org.loose.fis.sre.exceptions;

public class DoctorServiceAlreadyExistsException extends Exception{
    public DoctorServiceAlreadyExistsException() {
        super(String.format("Already exists!"));
    }
}