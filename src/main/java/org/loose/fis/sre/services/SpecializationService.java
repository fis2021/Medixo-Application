package org.loose.fis.sre.services;

import org.dizitart.no2.Nitrite;
import org.dizitart.no2.objects.ObjectRepository;
import org.loose.fis.sre.exceptions.SpecializationAlreadyExistException;
import org.loose.fis.sre.model.Specialization;

import java.util.Objects;

import static org.loose.fis.sre.services.FileSystemService.getPathToFile;

public class SpecializationService {

    public static ObjectRepository<Specialization> specializationsRepository;

    public static void initDatabase() {
        Nitrite database = Nitrite.builder()
                .filePath(getPathToFile("specializations.db").toFile())
                .openOrCreate("test", "test");

        specializationsRepository = database.getRepository(Specialization.class);

    }

    public static ObjectRepository<Specialization> getServicesRepository() {
        return specializationsRepository;
    }

    public static void addSpecialization(String specialization) throws SpecializationAlreadyExistException
    {
        checkSpecializationDoesNotAlreadyExist(specialization);
        Specialization s=new Specialization(specialization);
        specializationsRepository.insert(s);
    }

    static void checkSpecializationDoesNotAlreadyExist(String specialization) throws SpecializationAlreadyExistException{
        for (Specialization s : specializationsRepository.find()) {
            if (Objects.equals(specialization, s.getSpecialization()))
                throw new SpecializationAlreadyExistException(specialization);
        }
    }

}
