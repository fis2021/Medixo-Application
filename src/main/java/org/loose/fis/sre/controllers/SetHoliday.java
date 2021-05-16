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
import org.loose.fis.sre.model.WhoIsLoggedInfo;
import org.loose.fis.sre.services.AppointmentService;
import java.util.Objects;

public class SetHoliday {

    @FXML
    private Button goBackButton;

    @FXML
    private ChoiceBox day;

    @FXML
    private ChoiceBox month;

    @FXML
    private ChoiceBox year;

    @FXML
    private Text setHolidayMessage;


    @FXML
    public void initialize() {
        day.getItems().addAll("1","2","3","4","5","6","7","8","9","10","11","12","13","14","15","16","17","18",
                "19","20","21","22","23","24","25","26","27","28","29","30","31");
        month.getItems().addAll("January","February","March","April","May","June","July","August","September","October","November","December");
        year.getItems().addAll("2021","2022");
    }

    @FXML
    public void handleGoBackButtonAction() {
        try{
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("doctor_menu.fxml")));
            Stage scene= (Stage) goBackButton.getScene().getWindow();
            scene.setTitle("Medixo");
            scene.setScene(new Scene(root,725,490));
        } catch (Exception e1){
            System.out.println("Can't open the 'Doctor menu' window!");
        }
    }

    @FXML
    public void handleSetHolidayAction() throws AppointmentAlreadyExistException, DoctorDoesNotExistException, UnavailableDayException {
        try
        {
            AppointmentService.addAppointment((String) day.getValue(),(String) month.getValue(),(String) year.getValue(),"00:00", WhoIsLoggedInfo.getLoggedName(),
                    WhoIsLoggedInfo.getLoggedUsername());
            setHolidayMessage.setText("Holiday saved successfully!");
        }
        catch (IncorrectDateException e) {
            setHolidayMessage.setText(e.getMessage());
        }
        catch (IncorrectNameException e) {
            setHolidayMessage.setText(e.getMessage());
        }
    }
}
