package org.loose.fis.sre.exceptions;

public class SpecializationAlreadyExistException extends Exception{

    private String specialization;

    public SpecializationAlreadyExistException(String specialization) {
        super(String.format("The %s specialization already exists!", specialization));
        this.specialization=specialization;
    }

}
