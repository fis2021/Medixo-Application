package org.loose.fis.sre.exceptions;

public class AppointmentDoesNotExistException extends Exception{
    private String day;
    private String hour;
    private String month;
    private String year;


    public AppointmentDoesNotExistException(String day,String month, String year,String hour) {
        super(String.format("An appointment for %s/%s/%s at %s does not exists!", day,month,year,hour));
        this.day = day;
        this.month = month;
        this.year = year;
        this.hour = hour;

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
}
