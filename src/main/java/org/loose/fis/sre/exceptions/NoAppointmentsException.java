package org.loose.fis.sre.exceptions;

public class NoAppointmentsException extends Exception{
    private final String Name;

    public NoAppointmentsException (String Name) {
        super(String.format("No appointments for  %s!", Name));
        this.Name = Name;
    }

    public String Name() {
        return Name;
    }
}
