package org.loose.fis.sre.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import javafx.stage.Stage;
public class RegistrationController {
    @FXML
    private Button doctorButton;
    @FXML
    private Button patientButton;
    @FXML
    void handleDoctorAction(ActionEvent event) {
        try{
            Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("register_doctor_user.fxml"));
            Stage scene= (Stage) doctorButton.getScene().getWindow();
            scene.setTitle("Medixo");
            scene.setScene(new Scene(root,725,490));
        } catch (Exception e1){
            System.out.println("Can t open the Doctor window!");
        }
    }

    @FXML
    void handlePatientAction(ActionEvent event) {
        try{
            FXMLLoader fxmlLoader2 = new FXMLLoader(getClass().getResource("register_patient_user.fxml"));
            Parent root2 = (Parent) fxmlLoader2.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root2));
            stage.show();
        } catch (Exception e2){
            System.out.println("Can t open the Patient window!");
        }
    }

}