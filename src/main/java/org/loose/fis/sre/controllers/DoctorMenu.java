package org.loose.fis.sre.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class DoctorMenu {

    @FXML
    private Button addAppointmentButton;

    @FXML
    private Button editAppointmentButton;

    @FXML
    private Button seeAppointmentsButton;

    @FXML
    private Button setHolidayButton;

    @FXML
    private Button logOutButton;

    @FXML
    private Button deleteAppointment;

    @FXML
    void handleAddAppointmentAction(ActionEvent event) {
        try{
            Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("add_appointment_type.fxml"));
            Stage scene= (Stage) addAppointmentButton.getScene().getWindow();
            scene.setTitle("Medixo");
            scene.setScene(new Scene(root,725,490));
        } catch (Exception e1){
            System.out.println("Can t open the 'Add appointment type' window!");
        }

    }

    @FXML
    void handleEditAppointmentAction(ActionEvent event) {


    }

    @FXML
    void handleDeleteAppointmentAction(ActionEvent event) {
        try{
            Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("delete_appointment_type.fxml"));
            Stage scene= (Stage) logOutButton.getScene().getWindow();
            scene.setTitle("Medixo");
            scene.setScene(new Scene(root,725,490));
        } catch (Exception e2){
            System.out.println("Can t open the 'Log in' window!");
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

    @FXML
    void handleSeeTodaysAppointmentsButton(ActionEvent event) {

    }

    @FXML
    void handleSetHolidayButton(ActionEvent event) {

    }

}
