package org.loose.fis.sre.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.dizitart.no2.objects.ObjectRepository;
import org.loose.fis.sre.exceptions.AppointmentDoesNotExistException;
import org.loose.fis.sre.model.WhoIsLoggedInfo;
import org.loose.fis.sre.services.AppointmentService;
import org.loose.fis.sre.model.Appointment;

import java.awt.event.ActionEvent;
import java.io.IOException;
import java.util.Objects;
import java.util.Optional;

public class CancelAppointmentController {

    private static ObjectRepository<Appointment> appointmentObjectRepositoryRepository = AppointmentService.getAppointmentRepository();

    @FXML
    private ListView<String> appointmentsListView;

    @FXML
    private Text getMessage;

    @FXML
    private Button cancelAppointment;

    @FXML
    private Button goBack;

    @FXML
    private ChoiceBox<String> day;

    @FXML
    private ChoiceBox<String> month;

    @FXML
    private ChoiceBox<String> year;

    @FXML
    private ChoiceBox<String> hour;

    @FXML
    public void initialize() throws IOException {
        updateListView();
        day.getItems().addAll("1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18",
                "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31");
        month.getItems().addAll("January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December");
        year.getItems().addAll("2021", "2022");
        hour.getItems().addAll("9:00", "10:00", "11:00", "12:00", "13:00", "14:00", "15:00", "16:00");

    }


    public void updateListView() {
        ObservableList<String> items = FXCollections.observableArrayList();
        for (Appointment service : appointmentObjectRepositoryRepository.find()) {
            if (WhoIsLoggedInfo.getLoggedUsername().equals(service.getUser())) {
                String s = service.getDay() + "/" + service.getMonth() + "/" + service.getYear() + " at " + service.getHour() + ", doctor " + service.getDoctor();
                items.add(s);
                appointmentsListView.setItems(items);
            }
        }
    }


    @FXML
    public void handleCancelAppointmentAction(){
        try {
            {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Message");
                alert.setHeaderText("Are you sure you want to delete the appointment?");
                Optional<ButtonType> result = alert.showAndWait();
                if (result.get().equals(ButtonType.OK)) {
                    AppointmentService.deleteService(WhoIsLoggedInfo.getLoggedUsername(), day.getValue(), month.getValue(), year.getValue(), hour.getValue());

                    getMessage.setText("Appointment deleted successfully !");
                }
            }

            updateListView();
        }catch(AppointmentDoesNotExistException e){
            getMessage.setText(e.getMessage());
        }
    }

    @FXML
    public void handleGoBackAction() {
        try{
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("patient_menu.fxml")));
            Stage scene= (Stage) goBack.getScene().getWindow();
            scene.setTitle("Medixo");
            scene.setScene(new Scene(root,725,490));
        } catch (Exception e1){
            System.out.println("Can't open the 'Patient menu' window!");
        }
    }
}
