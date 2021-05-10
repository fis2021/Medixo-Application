package org.loose.fis.sre.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class AddAppointmentType {

    @FXML
    private TextField appointmentName;

    @FXML
    private TextField appointmentDescription;

    @FXML
    private TextField appointmentPrice;

    @FXML
    private Button addAppointment;

    @FXML
    private Button goBackButton;

    @FXML
    void handleAddAppointmentAction() {

    }

    @FXML
    void handleGoBackButtonAction() {
        try{
            Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("doctor_menu.fxml"));
            Stage scene= (Stage) goBackButton.getScene().getWindow();
            scene.setTitle("Medixo");
            scene.setScene(new Scene(root,725,490));
        } catch (Exception e2){
            System.out.println("Can t open the 'Doctor menu' window!");
        }

    }

}
