package org.loose.fis.sre.controllers;

import javafx.stage.Stage;
import org.loose.fis.sre.exceptions.AccountException;
import org.loose.fis.sre.exceptions.WrongPasswordException;
import org.loose.fis.sre.exceptions.UsernameDoesNotExistException;
import org.loose.fis.sre.exceptions.WrongRoleException;
import org.loose.fis.sre.model.User;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.scene.control.Button;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.Node;
import org.loose.fis.sre.model.WhoIsLoggedInfo;
import org.loose.fis.sre.services.UserService;



public class LoginController {
    @FXML
    private Button registerButton;

    @FXML
    private Text registrationMessage;

    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private ChoiceBox selectRole;

    @FXML
    public void initialize() {
        selectRole.getItems().addAll("Doctor", "Patient", "Manager");
    }

    @FXML
    private Text loginUsernameMessage;


    @FXML
    public void handleLoginAction(javafx.event.ActionEvent login) throws Exception {
        try {
            UserService.checkUserCredentials(usernameField.getText(), passwordField.getText(), (String) selectRole.getValue());
            loginUsernameMessage.setText("Login successfully!");
            String userRole = (String) selectRole.getValue();
            if (userRole.equals("Doctor")) {
                WhoIsLoggedInfo.setLoggedUsername(usernameField.getText());
                Parent root2 = FXMLLoader.load(getClass().getClassLoader().getResource("doctor_menu.fxml"));
                Stage window = (Stage) ((Node) login.getSource()).getScene().getWindow();
                ;
                window.setTitle("Doctor Menu");
                window.setScene(new Scene(root2, 725, 490));
                window.show();

            }
                if (userRole.equals("Patient")) {
                WhoIsLoggedInfo.setLoggedUsername(usernameField.getText());
                Parent root2 = FXMLLoader.load(getClass().getClassLoader().getResource("patient_menu.fxml"));
                Stage window = (Stage) ((Node) login.getSource()).getScene().getWindow();
                ;
                window.setTitle("Patient Menu");
                window.setScene(new Scene(root2, 725, 490));
                window.show();

                }

            if (userRole.equals("Manager")) {
                WhoIsLoggedInfo.setLoggedUsername(usernameField.getText());
                Parent root2 = FXMLLoader.load(getClass().getClassLoader().getResource("manager_menu.fxml"));
                Stage window = (Stage) ((Node) login.getSource()).getScene().getWindow();
                ;
                window.setTitle("Manager Menu");
                window.setScene(new Scene(root2, 725, 490));
                window.show();

            }

        } catch (UsernameDoesNotExistException e) {
            loginUsernameMessage.setText(e.getMessage());
        } catch (WrongPasswordException e) {
            loginUsernameMessage.setText(e.getMessage());
        }catch (WrongRoleException e){
            loginUsernameMessage.setText(e.getMessage());
        }

    }

    public void handleRegisterAction(javafx.event.ActionEvent login) throws Exception {
        Parent root1 = FXMLLoader.load(getClass().getClassLoader().getResource("register_choose_user.fxml"));
        Stage window = (Stage) ((Node) login.getSource()).getScene().getWindow();
        ;
        window.setTitle("Registration");
        window.setScene(new Scene(root1, 725, 490));
        window.show();
    }
}