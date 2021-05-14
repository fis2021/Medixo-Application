package org.loose.fis.sre.model;
import java.util.Objects;

public class Appointment {

    private String day;
    private String month;
    private String year;
    private String hour;
    private String doctor;
    private String user;

    public Appointment(String day,String month,String year,String hour, String doctor, String user) {
        this.user=user;
        this.day = day;
        this.month = month;
        this.year = year;
        this.hour = hour;
        this.doctor = doctor;
    }

    public Appointment(){

    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public void setHour(String hour) {
        this.hour = hour;
    }

    public void setDoctor(String doctor) {
        this.doctor = doctor;
    }

    public String getHour() {
        return hour;
    }

    public String getDoctor() {
        return doctor;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getDay() {
        return day;
    }


    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        if (!super.equals(object)) return false;
        Appointment appointment = (Appointment) object;
        return day.equals(appointment.day) && month.equals(appointment.month) && year.equals(appointment.year) && hour.equals(appointment.hour) && doctor.equals(appointment.doctor)  && user.equals(appointment.user);
    }

    public int hashCode() {
        return Objects.hash(super.hashCode(), day, month, year, hour, doctor, user);
    }

    @java.lang.Override
    public java.lang.String toString() {
        return "Appointment:" +
                " date: " + day  +
                "/" + month  +
                "/" + year  +
                ", at " + hour +
                ", doctor: " + doctor +
                ", patient: " + user  +
                ';'+ "\n" ;
    }
}