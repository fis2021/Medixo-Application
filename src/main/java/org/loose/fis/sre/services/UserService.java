package org.loose.fis.sre.services;

import org.dizitart.no2.Nitrite;
import org.dizitart.no2.objects.ObjectRepository;
import org.loose.fis.sre.exceptions.*;
import org.loose.fis.sre.model.User;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.loose.fis.sre.services.FileSystemService.getPathToFile;

public class UserService {

    private static ObjectRepository<User> userRepository;

    public static void initDatabase() {
        Nitrite database = Nitrite.builder()
                .filePath(getPathToFile("registration.db").toFile())
                .openOrCreate("test", "test");

        userRepository = database.getRepository(User.class);
    }

    public static void addUser(String username, String password, String role, String name, String age, String phoneNumber, String email, String specialization) throws UsernameAlreadyExistsException, InvalidCredentialException, InvalidEmailException  {
        checkUserDoesNotAlreadyExist(username);
        checkUsername(username);
        checkPassword(password);
        checkEmailAddress(email);
        checkPhoneNumber(phoneNumber);
        userRepository.insert(new User(username, encodePassword(username, password), role, name, age, phoneNumber, email, specialization));
    }
    public static void addUser(String username, String password, String role, String name, String age, String phoneNumber, String email) throws UsernameAlreadyExistsException, InvalidCredentialException, InvalidEmailException  {
        checkUserDoesNotAlreadyExist(username);
        checkUsername(username);
        checkPassword(password);
        checkEmailAddress(email);
        checkPhoneNumber(phoneNumber);
        userRepository.insert(new User(username, encodePassword(username, password), role, name, age, phoneNumber, email));
    }

    private static void checkUserDoesNotAlreadyExist(String username) throws UsernameAlreadyExistsException{
        for (User user : userRepository.find()) {
            if (Objects.equals(username, user.getUsername()))
                throw new UsernameAlreadyExistsException(username);
        }
    }

    public static void checkDoctorDoesExist(String doctor) throws DoctorDoesNotExistException {
        for (User user : userRepository.find()) {
            if (Objects.equals(user.getRole(), "Doctor") && Objects.equals(user.getName(), doctor))
                return;
        }
        throw new DoctorDoesNotExistException(doctor);
    }

    private static void checkUsername(String username) throws InvalidCredentialException{
        int ok = 1;
        String message = "";
        if (username.equals("")) {
            ok = 0;
            message = "Username cannot be empty!";
        } else if (username.length()<6){
            message = "Username should be at least 6 characters!";
            ok = 0;
        }else{
            String regex = "^[A-Za-z]\\w{5,}$";
            Pattern p = Pattern.compile(regex);
            Matcher m = p.matcher(username);
            if (!m.matches()){
                ok = 0;
                message = "Username should contain only letters, digits and underscore(_) and should start with a letter!";
            }
        }

        if (ok==0){
            throw new InvalidCredentialException(message);
        }
    }

    public static void checkUsernameA(String username) throws IncorrectNameException
    {
        for(User user : userRepository.find())
        {
            if(Objects.equals(username,user.getUsername()))
            {
                return;
            }
        }
        throw new IncorrectNameException(username);
    }

    private static void checkPassword(String password) throws InvalidCredentialException{
        int ok = 1;
        String message = "";
        if (password.equals("")) {
            message = "Password cannot be empty!";
            ok = 0;
        } else if (password.length()<8){
            message = "Password should be at least 8 characters!";
            ok = 0;
        } else if (!( password.matches(".*[0-9]{1,}.*") && password.matches(".*[A-Z]{1,}.*"))){
            message = "Password should contain at least one digit and one upper case character!";
            ok = 0;
        }

        if (ok==0){
            throw new InvalidCredentialException(message);
        }

    }

    private static void checkEmailAddress(String email) throws InvalidEmailException {
        String extension1 = "@yahoo.com";
        String extension2 = "@gmail.com";
        int i, j, k, sw1 = 0, sw2 = 0;
        for(i = 0; i < email.length(); i++)
        {
            if(email.charAt(i) == '@')
            {
                k = 0;
                for(j = i; j < email.length(); j++)
                {
                    if(extension1.charAt(k) != email.charAt(j))
                    {
                        sw1 = 1;
                        break;
                    }
                    k++;
                }
                break;
            }
        }
        for(i = 0; i < email.length(); i++)
        {
            if(email.charAt(i) == '@')
            {
                k = 0;
                for(j = i; j < email.length(); j++)
                {
                    if(extension2.charAt(k) != email.charAt(j))
                    {
                        sw2 = 1;
                        break;
                    }
                    k++;
                }
                break;
            }
        }
        if(sw1 == 1 && sw2 == 1)
        {
            throw new InvalidEmailException();
        }
    }

    private static void checkPhoneNumber(String phone) throws InvalidCredentialException{
        int ok = 1;
        String message = "";
        String regex = "\\d{10}";
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(phone);
        if (!m.matches()){
            ok = 0;
            message = "Invalid phone number! Must contain 10 digits.";
        }
        if (ok==0){
            throw new InvalidCredentialException(message);
        }
    }

    private static String encodePassword(String salt, String password) {
        MessageDigest md = getMessageDigest();
        md.update(salt.getBytes(StandardCharsets.UTF_8));

        byte[] hashedPassword = md.digest(password.getBytes(StandardCharsets.UTF_8));

        // This is the way a password should be encoded when checking the credentials
        return new String(hashedPassword, StandardCharsets.UTF_8)
                .replace("\"", ""); //to be able to save in JSON format
    }

    private static MessageDigest getMessageDigest() {
        MessageDigest md;
        try {
            md = MessageDigest.getInstance("SHA-512");
        } catch (NoSuchAlgorithmException e) {
            throw new IllegalStateException("SHA-512 does not exist!");
        }
        return md;
    }

    public static void checkUserCredentials(String username, String password, String role) throws UsernameDoesNotExistException, WrongPasswordException, WrongRoleException {
        int oku = 0, okp = 0, okr=0;
        for (User user : userRepository.find()) {
            if (Objects.equals(username, user.getUsername())) {
                oku = 1;
                if(encodePassword(username,password).equals(user.getPassword())) {
                    okp = 1;
                if (Objects.equals(role, user.getRole()))
                    okr=1;
                }
            }


        }
        if (oku == 0)
            throw new UsernameDoesNotExistException(username);
        if (okp == 0)
            throw new WrongPasswordException();
        if (okr == 0)
            throw new WrongRoleException();

    }

    public static String findName(String username){
        for (User user : userRepository.find()){
            if(Objects.equals(username, user.getUsername()))
                return user.getName();
        }
        return "";
    }

    public static void CheckNameCredentials (String Name) throws NoAppointmentsException
    {

        for (User user : userRepository.find()) {
            if (Objects.equals(Name, user.getName()))
            {
                return;
            }
        }
        throw new NoAppointmentsException(Name);

    }
}