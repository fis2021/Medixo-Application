package org.loose.fis.sre.controllers;

import org.loose.fis.sre.exceptions.InvalidCredentialException;
import org.loose.fis.sre.exceptions.InvalidEmailException;
import org.loose.fis.sre.exceptions.UsernameAlreadyExistsException;
import org.loose.fis.sre.services.UserService;

import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;


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

}
