package org.loose.fis.sre.exceptions;

public class NoAppointmentTypeException extends Exception{

    public NoAppointmentTypeException () {
        super(String.format("No appointment types!"));

    }
}
