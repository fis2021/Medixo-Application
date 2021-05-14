package org.loose.fis.sre.controllers;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.loose.fis.sre.exceptions.InvalidCredentialException;
import org.loose.fis.sre.exceptions.InvalidEmailException;
import org.loose.fis.sre.exceptions.UsernameAlreadyExistsException;
import org.loose.fis.sre.services.UserService;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

public class PatientRegistrationController {
    @FXML
    private TextField patientUsernameField;

    @FXML
    private TextField patientPhoneNumberField;

    @FXML
    private TextField patientEmailField;

    @FXML
    private PasswordField patientPasswordField;

    @FXML
    private TextField patientNameField;

    @FXML
    private TextField patientAgeField;

    @FXML
    private Text registrationMessage;

    @FXML
    void handleRegisterAction() {
        try {
            UserService.addUser(patientUsernameField.getText(), patientPasswordField.getText(), "Patient", patientNameField.getText(), patientAgeField.getText(), patientPhoneNumberField.getText(), patientEmailField.getText(), null);
            registrationMessage.setText("Account created successfully!");
        } catch (InvalidCredentialException e2){
            registrationMessage.setText(e2.getMessage());
        } catch (UsernameAlreadyExistsException e1) {
            registrationMessage.setText(e1.getMessage());
        } catch (InvalidEmailException e3 ){
            registrationMessage.setText(e3.getMessage());
        }
    }

    public void handleLoginAction(javafx.event.ActionEvent login) throws Exception {
        Parent root1 = FXMLLoader.load(getClass().getClassLoader().getResource("login.fxml"));
        Stage window = (Stage) ((Node) login.getSource()).getScene().getWindow();
        ;
        window.setTitle("Log In");
        window.setScene(new Scene(root1, 725, 490));
        window.show();
    }
}