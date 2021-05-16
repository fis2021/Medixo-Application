package org.loose.fis.sre.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.dizitart.no2.objects.ObjectRepository;
import org.loose.fis.sre.exceptions.DoctorServiceAlreadyExistsException;
import org.loose.fis.sre.exceptions.EmptyTextfieldsException;
import org.loose.fis.sre.model.DoctorService;
import org.loose.fis.sre.model.WhoIsLoggedInfo;
import org.loose.fis.sre.services.DoctorFacilitiesService;

import java.io.IOException;

public class EditAppointmentType {

    private static ObjectRepository<DoctorService> appointmentTypesRepository = DoctorFacilitiesService.getServicesRepository();

    @FXML
    private ListView<String> appointmentListView =  new ListView<String>();

    @FXML
    private Button saveEdit;

    @FXML
    private Button goBack;

    @FXML
    private TextField price;

    @FXML
    private TextField description;

    @FXML
    private TextField appointmentTypeName;

    @FXML
    private Text message;

    @FXML
    public void initialize() throws IOException {
        updateListView();
    }

    @FXML
    void handleEditAppointmentTypeAction() {
        if (appointmentListView.getSelectionModel().getSelectedItem()==null){
            message.setText("No service selected!");
        }

        try{
            DoctorFacilitiesService.editAppointmentType(WhoIsLoggedInfo.getLoggedUsername(),appointmentListView.getSelectionModel().getSelectedItem().toString(),
                    appointmentTypeName.getText(), description.getText(), price.getText());
            updateListView();
            message.setText("Service edited successfully !");

        }
        catch (EmptyTextfieldsException e){
            message.setText(e.getMessage());
        }
        catch(DoctorServiceAlreadyExistsException e){
            message.setText(e.getMessage());
        }

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
        String s="";
        for (DoctorService service : appointmentTypesRepository.find()) {
            if (WhoIsLoggedInfo.getLoggedUsername().equals(service.getUsername())) {
//                s=service.getServiceName() + "/" + service.getDescription() + "/" + service.getPrice();
//                items.add(s);
                items.add(service.getServiceName());
                appointmentListView.setItems(items);
            }
        }
    }

}

