package org.loose.fis.sre.services;

import org.dizitart.no2.Nitrite;
import org.dizitart.no2.objects.ObjectRepository;
import org.loose.fis.sre.exceptions.*;
import org.loose.fis.sre.model.Appointment;

import java.util.List;
import java.util.Objects;

import static org.loose.fis.sre.services.FileSystemService.getPathToFile;

public class AppointmentService {

    private static ObjectRepository<Appointment> appointmentRepository;

    public static void initDatabase() {
        Nitrite database = Nitrite.builder()
                .filePath(getPathToFile("appointment-database.db").toFile())
                .openOrCreate("app", "app");

        appointmentRepository = database.getRepository(Appointment.class);
    }

    public static List<Appointment> getAllAppointments() {
        return appointmentRepository.find().toList();
    }
    public static ObjectRepository<Appointment> getAppointmentRepository()
    {
        return appointmentRepository;
    }
    public static void addAppointment(String day,String month,String year,String hour, String doctor, String user) throws AppointmentAlreadyExistException, IncorrectNameException,  IncorrectDateException, DoctorDoesNotExistException
    {
        checkAppointmentDoesNotAlreadyExist(day,month,year,hour,doctor);
        checkDate(day,month,year);
        UserService.checkDoctorDoesExist(doctor);
        UserService.checkUsernameA(user);
        Appointment app=new Appointment(day,month,year,hour,doctor,user);
        appointmentRepository.insert(app);
    }



    static void checkAppointmentDoesNotAlreadyExist(String day,String month,String year,String hour,String doctor) throws AppointmentAlreadyExistException {
        for (Appointment appointment : appointmentRepository.find()) {
            if (Objects.equals(day, appointment.getDay()) && Objects.equals(month, appointment.getMonth()) && Objects.equals(year, appointment.getYear()) && Objects.equals(hour, appointment.getHour())  && Objects.equals(doctor, appointment.getDoctor()))
                throw new AppointmentAlreadyExistException(day,month,year,hour,doctor);
        }
    }
    private static void checkDate(String day,String month,String year) throws IncorrectDateException {
        if((Objects.equals(day,"30") || Objects.equals(day,"31")) &&(Objects.equals(month,"February")))
        {
            throw new IncorrectDateException(day,month,year);
        }
        if((Objects.equals(day,"31")) && (Objects.equals(month,"April") || Objects.equals(month,"June") || Objects.equals(month,"September") ||  Objects.equals(month,"November")))
        {
            throw new IncorrectDateException(day,month,year);
        }
    }

    public static String  seeAppointments(String user) throws NoAppointmentsException, IncorrectNameException
    {
        UserService.checkUsernameA(user);
        String s="";
        for (Appointment  appointment : appointmentRepository.find())
        {
            if(Objects.equals(user, appointment.getUser())) {
                s = s + appointment.toString();
                s = s + "\n";
            }
        }
        return s;
    }
    public static String  seeHistoryAppointments (String Name) throws NoAppointmentsException
    {
        UserService.CheckNameCredentials(Name);
        String s="";
        for (Appointment  appointment : appointmentRepository.find())
        {
            if(Objects.equals(Name, appointment.getUser())) {
                s = s + appointment.toString();
                s = s + "\n";
            }
        }
        return s;
    }

}
