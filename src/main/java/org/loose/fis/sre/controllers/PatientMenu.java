package org.loose.fis.sre.controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class PatientMenu {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button makeAppointmentButton;

    @FXML
    private Button cancelAppointmentButton;

    @FXML
    private Button seeAllAppointmentsButton;

    @FXML
    private Button logOutButton;

    @FXML
    void handleCancelAppointmentAction(ActionEvent event) {
        try{
            Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("cancel_appointment.fxml"));
            Stage scene= (Stage) cancelAppointmentButton.getScene().getWindow();
            scene.setTitle("Medixo");
            scene.setScene(new Scene(root,725,490));
        } catch (Exception e2){
            System.out.println("Can t open the 'Cancel an appointment' window!");
        }
    }

    @FXML
    void handleMakeAppointmentAction(ActionEvent event) {
        try{
            Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("make_appointment.fxml"));
            Stage scene= (Stage) makeAppointmentButton.getScene().getWindow();
            scene.setTitle("Medixo");
            scene.setScene(new Scene(root,725,490));
        } catch (Exception e2){
            System.out.println("Can t open the 'Make an appointment' window!");
        }
    }

    @FXML
    void handleSeeAllAppointmentsButton(ActionEvent event) {
        try{
            Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("see_all_appointments.fxml"));
            Stage scene= (Stage) seeAllAppointmentsButton.getScene().getWindow();
            scene.setTitle("Medixo");
            scene.setScene(new Scene(root,725,490));
        } catch (Exception e2){
            System.out.println("Can t open the 'See all appointments' window!");
        }
    }


    @FXML
    void handleLogOutAction(ActionEvent event) {
        try{
            Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("login.fxml"));
            Stage scene= (Stage) logOutButton.getScene().getWindow();
            scene.setTitle("Medixo");
            scene.setScene(new Scene(root,725,490));
        } catch (Exception e2){
            System.out.println("Can t open the 'Log in' window!");
        }
    }
}

