package org.loose.fis.sre.services;

import org.dizitart.no2.Nitrite;
import org.dizitart.no2.objects.ObjectRepository;
import org.loose.fis.sre.exceptions.DoctorServiceAlreadyExistsException;
import org.loose.fis.sre.exceptions.EmptyTextfieldsException;
import org.loose.fis.sre.exceptions.IncorrectNameException;
import org.loose.fis.sre.exceptions.NoAppointmentsException;
import org.loose.fis.sre.model.Appointment;
import org.loose.fis.sre.model.DoctorService;
import org.loose.fis.sre.model.WhoIsLoggedInfo;

import java.util.Objects;

import static org.dizitart.no2.objects.filters.ObjectFilters.eq;

public class DoctorFacilitiesService {

    private static ObjectRepository<DoctorService> servicesRepository;

    public static ObjectRepository<DoctorService> getServicesRepository() {
        return servicesRepository;
    }

    public static void initDatabase() {
        Nitrite database = Nitrite.builder()
                .filePath(FileSystemService.getPathToFile("medixo-appointmentTypes.db").toFile())
                .openOrCreate("test", "test");

        servicesRepository = database.getRepository(DoctorService.class);
    }

    public static void addService(String username,String appointmentTypeName, String description, String price) throws EmptyTextfieldsException, DoctorServiceAlreadyExistsException{
        checkEmptyTextFields(appointmentTypeName);
        checkServiceExists(appointmentTypeName);
        servicesRepository.insert(new DoctorService(username, appointmentTypeName, description, price));
    }

    private static void checkEmptyTextFields(String serviceName) throws EmptyTextfieldsException {
        if (serviceName.equals(""))
            throw new EmptyTextfieldsException();
    }

    public static void deleteService(String username,String appointmentTypeName){
        DoctorService service_aux = new  DoctorService();

        for ( DoctorService service : servicesRepository.find()){
            if (username.equals(service.getUsername()) && appointmentTypeName.equals(service.getServiceName())) {
                service_aux = service;
            }
        }

        servicesRepository.remove(eq("appointmentTypeName",appointmentTypeName),service_aux);
    }

    public static void editAppointmentType(String username, String appointmentTypeNameOld, String appointmentTypeNameNew,  String descriptionNew,  String priceNew) throws EmptyTextfieldsException, DoctorServiceAlreadyExistsException {
        checkEmptyTextFields(appointmentTypeNameNew);
        checkEmptyTextFields(descriptionNew);
        checkEmptyTextFields(priceNew);
        checkServiceExists(appointmentTypeNameNew);
        DoctorService service_aux = new DoctorService();

        for (DoctorService service : servicesRepository.find()) {
            if (username.equals(service.getUsername()) && appointmentTypeNameOld.equals(service.getServiceName())) {
                service_aux = service;
            }
        }
        if (!appointmentTypeNameNew.equals("")){
            service_aux.setAppointmentTypeName(appointmentTypeNameNew);
            service_aux.setDescription(descriptionNew);
            service_aux.setPrice(priceNew);
        }

        servicesRepository.update(eq("appointmentTypeName", appointmentTypeNameOld), service_aux);
    }

    private static void checkServiceExists(String serviceName)  throws DoctorServiceAlreadyExistsException {

        int ok = 0;
        for (DoctorService service : servicesRepository.find()){
            if (WhoIsLoggedInfo.getLoggedUsername().equals(service.getUsername()))
                if (serviceName.equals(service.getServiceName()))
                    ok = 1;
        }

        if (ok == 1) throw new DoctorServiceAlreadyExistsException();

    }
}
