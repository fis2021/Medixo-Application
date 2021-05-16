package org.loose.fis.sre.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.loose.fis.sre.exceptions.DoctorServiceAlreadyExistsException;
import org.loose.fis.sre.exceptions.EmptyTextfieldsException;
import org.loose.fis.sre.model.WhoIsLoggedInfo;
import org.loose.fis.sre.services.DoctorFacilitiesService;

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
    private Text addText;

    @FXML
    void handleAddAppointmentAction() {
        try{
            DoctorFacilitiesService.addService(WhoIsLoggedInfo.getLoggedUsername(),appointmentName.getText(), appointmentDescription.getText(), appointmentPrice.getText());
            addText.setText("Service added successfully !");
        }catch (EmptyTextfieldsException e){
            addText.setText(e.getMessage());
        }
        catch(DoctorServiceAlreadyExistsException e){
            addText.setText(e.getMessage());
        }
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
