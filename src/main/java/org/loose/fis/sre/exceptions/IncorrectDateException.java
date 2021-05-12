package org.loose.fis.sre.exceptions;

public class IncorrectDateException extends Exception {

    private final String day;
    private final String month;
    private final String year;

    public IncorrectDateException(String day,String month, String year) {
        super(String.format("Date %s %s %s is incorrect", day,month,year));
        this.day = day;
        this.month = month;
        this.year = year;
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
}