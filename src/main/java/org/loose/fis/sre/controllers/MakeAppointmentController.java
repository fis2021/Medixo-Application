package org.loose.fis.sre.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.loose.fis.sre.exceptions.*;
import org.loose.fis.sre.services.AppointmentService;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.util.Objects;

public class MakeAppointmentController {

    @FXML
    private Button makeAppointment;

    @FXML
    private Button goBackButton;

    @FXML
    private ChoiceBox day;

    @FXML
    private ChoiceBox month;

    @FXML
    private ChoiceBox year;

    @FXML
    private ChoiceBox hour;

    @FXML
    private TextField doctor;

    @FXML
    private Text make_appointmentMessage;

    @FXML
    private TextField username;

    @FXML
    public void initialize() {
        day.getItems().addAll("1","2","3","4","5","6","7","8","9","10","11","12","13","14","15","16","17","18",
                "19","20","21","22","23","24","25","26","27","28","29","30","31");
        month.getItems().addAll("January","February","March","April","May","June","July","August","September","October","November","December");
        year.getItems().addAll("2021","2022");
        hour.getItems().addAll("9:00", "10:00", "11:00", "12:00", "13:00", "14:00", "15:00", "16:00");
    }

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

    @FXML
    public void handleMakeAppointmentAction(){
        try
        {
            AppointmentService.addAppointment((String) day.getValue(),(String) month.getValue(),(String) year.getValue(),(String) hour.getValue(),
                    doctor.getText(),username.getText());
            make_appointmentMessage.setText("Appointment saved successfully!");
        }
        catch (IncorrectDateException e) {
            make_appointmentMessage.setText(e.getMessage());
        }
        catch (IncorrectNameException e) {
            make_appointmentMessage.setText(e.getMessage());
        }
        catch (DoctorDoesNotExistException e) {
            make_appointmentMessage.setText(e.getMessage());
        } catch (AppointmentAlreadyExistException e) {
            make_appointmentMessage.setText(e.getMessage());
        } catch (UnavailableDayException e) {
            make_appointmentMessage.setText(e.getMessage());
        }
    }
}
