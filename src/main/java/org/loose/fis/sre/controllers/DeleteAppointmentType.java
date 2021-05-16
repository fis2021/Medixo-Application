package org.loose.fis.sre.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ListView;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.dizitart.no2.objects.ObjectRepository;
import org.loose.fis.sre.model.DoctorService;
import org.loose.fis.sre.model.WhoIsLoggedInfo;
import org.loose.fis.sre.services.DoctorFacilitiesService;

import java.io.IOException;
import java.util.Optional;

public class DeleteAppointmentType {

    private static ObjectRepository<DoctorService> appointmentTypesRepository = DoctorFacilitiesService.getServicesRepository();

    @FXML
    private ListView<String> appointmentListView =  new ListView<String>();

    @FXML
    private Text getMessage;

    @FXML
    private Button deleteAppointmentType;

    @FXML
    private Button goBack;

    @FXML
    public void initialize() throws IOException {
        updateListView();
    }

    @FXML
    void handleDeleteAppointmentTypeAction() throws Exception{

        if (appointmentListView.getSelectionModel().getSelectedItem()==null){
            getMessage.setText("No service selected!");
        }
        else{
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Message");
            alert.setHeaderText("Are you sure you want to delete the appointment type?");
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get().equals(ButtonType.OK)) {
                DoctorFacilitiesService.deleteService(WhoIsLoggedInfo.getLoggedUsername(),appointmentListView.getSelectionModel().getSelectedItem().toString());

                getMessage.setText("Service deleted successfully !");
            }
        }
        updateListView();
    }

    @FXML
    void handleGoBackAction() {
        try{
            Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("doctor_menu.fxml"));
            Stage scene= (Stage) goBack.getScene().getWindow();
            scene.setTitle("Medixo");
            scene.setScene(new Scene(root,725,490));
        } catch (Exception e2){
            System.out.println("Can t open the 'Doctor menu' window!");
        }

    }

    public void updateListView(){
        ObservableList<String> items = FXCollections.observableArrayList();
        for (DoctorService service : appointmentTypesRepository.find()) {
            if (WhoIsLoggedInfo.getLoggedUsername().equals(service.getUsername())) {
                items.add(service.getServiceName());
                appointmentListView.setItems(items);
            }
        }
    }

}
