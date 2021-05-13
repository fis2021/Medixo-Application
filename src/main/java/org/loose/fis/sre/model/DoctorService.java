package org.loose.fis.sre.model;
import org.dizitart.no2.RemoveOptions;

public class DoctorService extends RemoveOptions {

    private String username;
    private String serviceName;
    private String description;
    private String price;

    public DoctorService() {
    }

    public DoctorService(String username,String serviceName, String description, String price) {
        this.username = username;
        this.description = description;
        this.serviceName = serviceName;
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public  String getUsername() {
        return username;
    }

    public String getServiceName(){
        return serviceName;
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

    public void setServiceName(String serviceName){
        this.serviceName = serviceName;
    }

    public void setPrice (String price){
        this.price = price;
    }
}
