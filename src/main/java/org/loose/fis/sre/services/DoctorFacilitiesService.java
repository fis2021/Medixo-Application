package org.loose.fis.sre.services;

import org.dizitart.no2.Nitrite;
import org.dizitart.no2.objects.ObjectRepository;
import org.loose.fis.sre.exceptions.DoctorServiceAlreadyExistsException;
import org.loose.fis.sre.exceptions.EmptyTextfieldsException;
import org.loose.fis.sre.model.DoctorService;
import org.loose.fis.sre.services.FileSystemService;

import java.util.Objects;
import static org.dizitart.no2.objects.filters.ObjectFilters.eq;

public class DoctorFacilitiesService {

    private static ObjectRepository<DoctorService> servicesRepository;

    public static ObjectRepository<DoctorService> getServicesRepository() {
        return servicesRepository;
    }

    public static void initDatabase() {
        Nitrite database = Nitrite.builder()
                .filePath(FileSystemService.getPathToFile("medixo-services.db").toFile())
                .openOrCreate("test", "test");

        servicesRepository = database.getRepository(DoctorService.class);
    }

    public static void addService(String username,String serviceName, String description, int price) throws EmptyTextfieldsException, DoctorServiceAlreadyExistsException{
        checkEmptyTextFields(serviceName);
        checkServiceExists(serviceName);
        servicesRepository.insert(new DoctorService(username, serviceName, description, price));
    }
    private static void checkEmptyTextFields(String serviceName) throws EmptyTextfieldsException {
        if (serviceName.equals(""))
            throw new EmptyTextfieldsException();
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
