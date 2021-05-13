package org.loose.fis.sre.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.loose.fis.sre.exceptions.IncorrectNameException;
import org.loose.fis.sre.exceptions.NoAppointmentsException;
import org.loose.fis.sre.model.User;
import org.loose.fis.sre.model.WhoIsLoggedInfo;
import org.loose.fis.sre.services.AppointmentService;


import java.util.Objects;

public class SeeAllAppointmentsController {

    @FXML
    private Button goBackButton;

    @FXML
    private TextArea textArea;


    @FXML
    public void handleGoBackButtonAction() {
        try{
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("patient_menu.fxml")));
            Stage scene= (Stage) goBackButton.getScene().getWindow();
            scene.setTitle("Medixo");
            scene.setScene(new Scene(root,725,490));
        } catch (Exception e1){
            System.out.println("Can't open the 'Patient menu' window!");
        }
    }

    public void handleShowAppointmentsAction() {
        try {
            textArea.setText(AppointmentService.seeAppointments(WhoIsLoggedInfo.getLoggedUsername()));
        } catch (NoAppointmentsException e) {
            textArea.setText(e.getMessage());
        } catch (IncorrectNameException e){
            textArea.setText(e.getMessage());
        }
    }
}
