package org.loose.fis.sre.exceptions;

public class AppointmentAlreadyExistException extends Exception{
    private final String day;
    private final String hour;
    private final String month;
    private final String year;
    private final String doctor;

    public AppointmentAlreadyExistException(String day,String month, String year,String hour,String doctor) {
        super(String.format("A appointment for %s %s %s at %s with the doctor %s already exists!", day,month,year,hour,doctor));
        this.day = day;
        this.month = month;
        this.year = year;
        this.hour = hour;
        this.doctor = doctor;
    }



    public String getDay() {
        return day;
    }

    public String getMonth() {
        return month;
    }

    public String getYear() {
        return year;
    }

    public String getHour() {
        return hour;
    }

    public String getDoctor() {
        return doctor;
    }
}
