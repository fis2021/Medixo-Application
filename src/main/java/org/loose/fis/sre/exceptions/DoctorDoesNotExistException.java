package org.loose.fis.sre.exceptions;

public class DoctorDoesNotExistException extends Exception{
    private final String doctor;

    public DoctorDoesNotExistException(String doctor) {
        super(String.format("A doctor with the name %s does not exist!", doctor));
        this.doctor = doctor;

    }

    public String getDoctor() {
        return doctor;
    }
}
