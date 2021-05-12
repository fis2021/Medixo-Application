package org.loose.fis.sre.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.loose.fis.sre.exceptions.InvalidCredentialException;
import org.loose.fis.sre.exceptions.InvalidEmailException;
import org.loose.fis.sre.exceptions.UsernameAlreadyExistsException;
import org.loose.fis.sre.services.UserService;

import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.scene.control.Button;


public class DoctorRegistrationController {
    @FXML
    private TextField doctorUsernameField;

    @FXML
    private TextField doctorPhoneNumberField;

    @FXML
    private TextField doctorEmailField;

    @FXML
    private PasswordField doctorPasswordField;

    @FXML
    private TextField doctorNameField;

    @FXML
    private TextField doctorAgeField;

    @FXML
    private ChoiceBox specialization;


    @FXML
    private Text registrationMessage;

    @FXML
    private Button backButton;

    @FXML
    public void initialize() {
        specialization.getItems().addAll("Dermatologist", "Cardiologist", "Neurologist", "Pediatrician");
    }

    @FXML
    void handleRegisterAction() {
        try {
            UserService.addUser(doctorUsernameField.getText(), doctorPasswordField.getText(), "Doctor", doctorNameField.getText(), doctorAgeField.getText(), doctorPhoneNumberField.getText(), doctorEmailField.getText(), (String) specialization.getValue());
            registrationMessage.setText("Account created successfully!");
        } catch (InvalidCredentialException e2){
            registrationMessage.setText(e2.getMessage());
        } catch (UsernameAlreadyExistsException e1) {
            registrationMessage.setText(e1.getMessage());
        } catch (InvalidEmailException e3 ){
            registrationMessage.setText(e3.getMessage());
        }
    }
    @FXML
    void handleBackAction() {
        try{
            Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("login.fxml"));
            Stage scene= (Stage) backButton.getScene().getWindow();
            scene.setTitle("Medixo");
            scene.setScene(new Scene(root,725,490));
        } catch (Exception e1){
            System.out.println("Can t open the 'Add appointment type' window!");
        }

    }


}
