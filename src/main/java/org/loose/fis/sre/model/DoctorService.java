package org.loose.fis.sre.model;
import org.dizitart.no2.RemoveOptions;

public class DoctorService extends RemoveOptions {

    private String username;
    private String appointmentTypeName;
    private String description;
    private String price;

    public DoctorService() {
    }

    public DoctorService(String username,String appointmentTypeName, String description, String price) {
        this.username = username;
        this.description = description;
        this.appointmentTypeName = appointmentTypeName;
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public  String getUsername() {
        return username;
    }

    public String getServiceName(){
        return appointmentTypeName;
    }

    public String getPrice(){
        return price;
    }



    public void setUsername(String username) {
        this.username = username;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setAppointmentTypeName(String appointmentTypeName){
        this.appointmentTypeName = appointmentTypeName;
    }

    public void setPrice (String price){
        this.price = price;
    }
}
