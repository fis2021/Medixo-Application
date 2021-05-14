package org.loose.fis.sre.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.loose.fis.sre.exceptions.SpecializationAlreadyExistException;
import org.loose.fis.sre.services.*;

public class AddSpecializationController {

    @FXML
    private TextField sname;

    @FXML
    private Button addSpecialization;

    @FXML
    private Button goBackButton;

    @FXML
    private Text messageField;

    @FXML
    void handleAddSpecialization(){
        try{
            SpecializationService.addSpecialization(sname.getText());
            messageField.setText("Specialization saved successfully!");
        }catch(SpecializationAlreadyExistException e){
            messageField.setText(e.getMessage());
        }
    }

    @FXML
    public void handleGoBackButtonAction() {
        try{
            Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("login.fxml"));
            Stage scene= (Stage) goBackButton.getScene().getWindow();
            scene.setTitle("Medixo");
            scene.setScene(new Scene(root,725,490));
        } catch (Exception e2){
            System.out.println("Can t open the 'Log in' window!");
        }
    }

}
