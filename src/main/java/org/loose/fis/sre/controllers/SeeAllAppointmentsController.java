package org.loose.fis.sre.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.dizitart.no2.objects.ObjectRepository;
import org.loose.fis.sre.exceptions.IncorrectNameException;
import org.loose.fis.sre.exceptions.NoAppointmentsException;
import org.loose.fis.sre.model.Appointment;
import org.loose.fis.sre.model.User;
import org.loose.fis.sre.model.WhoIsLoggedInfo;
import org.loose.fis.sre.services.AppointmentService;

import javafx.scene.text.Text;


import java.io.IOException;
import java.util.Objects;

public class SeeAllAppointmentsController {

    private static ObjectRepository<Appointment> appointmentObjectRepositoryRepository = AppointmentService.getServicesRepository();

    @FXML
    private Button goBackButton;

    @FXML
    private Text response;

    @FXML
    private ListView<String> appointmentsListView;

    @FXML
    public void initialize() throws IOException {
        try {
            updateListView();
        }
        catch(NoAppointmentsException e) {
            response.setText(e.getMessage());
        }
    }

    public void updateListView() throws NoAppointmentsException{
        int ok=0;
        ObservableList<String> items = FXCollections.observableArrayList();
        for (Appointment service : appointmentObjectRepositoryRepository.find()) {
            if (WhoIsLoggedInfo.getLoggedUsername().equals(service.getUser())) {
                String s = service.getDay() + "/" + service.getMonth() + "/" + service.getYear() + " at " + service.getHour() + ", doctor " + service.getDoctor();
                items.add(s);
                appointmentsListView.setItems(items);
                ok=1;
            }
        }
        if (ok==0)
            throw new NoAppointmentsException(WhoIsLoggedInfo.getLoggedUsername());
    }


    @FXML
    public void handleGoBackAction() {
        try{
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("patient_menu.fxml")));
            Stage scene= (Stage) goBackButton.getScene().getWindow();
            scene.setTitle("Medixo");
            scene.setScene(new Scene(root,725,490));
        } catch (Exception e1){
            System.out.println("Can't open the 'Patient menu' window!");
        }
    }
}
