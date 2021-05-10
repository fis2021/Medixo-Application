package org.loose.fis.sre.controllers;

import javafx.stage.Stage;
import org.loose.fis.sre.exceptions.AccountException;
import org.loose.fis.sre.model.User;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.scene.control.Button;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;



public class LoginController {
    @FXML
    private Button registerButton;

    @FXML
    private Text registrationMessage;

    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;



    public void handleLoginAction() throws Exception{
        Stage primary = (Stage) registrationMessage.getScene().getWindow();

        try {
            String role = User.getRole();
            User.setUsername(usernameField.getText());
            if(role.equals("Doctor")){
                Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("fxml/doctor_menu.fxml"));
                Scene nextScene = new Scene(root, 725,490);

                primary.setScene(nextScene);
            }
            else
            if(role.equals("Pacient")){
                Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("fxml/patient_menu.fxml"));
                Scene nextScene = new Scene(root, 725,490);

                primary.setScene(nextScene);
            }

        }catch (AccountException e) {
            registrationMessage.setText(e.getMessage());
        }
    }

    public void handleRegisterAction() throws Exception{

        Stage primary = (Stage) registrationMessage.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("fxml/register_choose_user.fxml"));
        Scene nextScene = new Scene(root, 725,490);

        primary.setScene(nextScene);
    }
}
