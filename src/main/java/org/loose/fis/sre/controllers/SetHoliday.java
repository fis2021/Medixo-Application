package org.loose.fis.sre.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class SetHoliday {

    @FXML
    private TextField firstDay;

    @FXML
    private TextField lastDay;

    @FXML
    private Button goBack;

    @FXML
    private Button setHoliday;

    @FXML
    void handleGoBackButton(ActionEvent event) {
        try{
            Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("doctor_menu.fxml"));
            Stage scene= (Stage) goBack.getScene().getWindow();
            scene.setTitle("Medixo");
            scene.setScene(new Scene(root,725,490));
        } catch (Exception e2){
            System.out.println("Can t open the 'Doctor menu' window!");
        }


    }

    @FXML
    void handleSetHolidayButton(ActionEvent event) {

    }

}
