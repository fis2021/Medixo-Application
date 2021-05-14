package org.loose.fis.sre.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;
import org.dizitart.no2.objects.ObjectRepository;
import org.loose.fis.sre.exceptions.NoAppointmentsException;
import org.loose.fis.sre.model.Appointment;
import javafx.scene.text.Text;
import org.loose.fis.sre.model.WhoIsLoggedInfo;
import org.loose.fis.sre.services.AppointmentService;

import java.util.Objects;

public class SeeTodaysAppointments {

    private static ObjectRepository<Appointment> appointmentsRepository = AppointmentService.getAppointmentRepository();

    @FXML
    private ChoiceBox setDay;

    @FXML
    private ChoiceBox setMonth;

    @FXML
    private ChoiceBox setYear;

    @FXML
    private Button goBack;

    @FXML
    private Button showAppointments;

    @FXML
    private Text message;


    @FXML
    private ListView<String> appointmentsList = new ListView<String>();;

    public void initialize() {
        setYear.getItems().addAll("2021", "2022");
        setMonth.getItems().addAll("January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December");
        setDay.getItems().addAll("1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31");
    }


    @FXML
    void handleGoBackButton() {
        try {
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("doctor_menu.fxml")));
            Stage scene = (Stage) goBack.getScene().getWindow();
            scene.setScene(new Scene(root, 725, 490));
            scene.setTitle("Medixo");
        } catch (Exception e2) {
            System.out.println("Can t open the 'Doctor menu' window!");
        }

    }

    @FXML
    public void handleShowAppointmentsAction() {
//        try {
//            seeAppointments.setText(AppointmentService.seeAppointments(WhoIsLoggedInfo.getLoggedUsername(),setDay.getAccessibleText(), setMonth.getAccessibleText(), setYear.getAccessibleText()));
//        } catch (NoAppointmentsException e) {
//            seeAppointments.setText(e.getMessage());
//        } catch (IncorrectNameException e){
//            seeAppointments.setText(e.getMessage());
//        }
            updateListView();

    }

    public void updateListView() {

        ObservableList<String> items = FXCollections.observableArrayList();
        String s = "";
        for (Appointment appointment : appointmentsRepository.find()) {
            if (WhoIsLoggedInfo.getLoggedUsername().equals(appointment.getDoctor()) && setDay.getValue().equals(appointment.getDay()) &&
                    setMonth.getValue().equals(appointment.getMonth()) && setYear.getValue().equals(appointment.getYear())) {
                s=appointment.getUser() + " PROGRAMAT LA ORA: " + appointment.getHour();
                items.add(s);
                appointmentsList.setItems(items);
            }
        }


    }
}
