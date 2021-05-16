package org.loose.fis.sre.services;

import static org.junit.jupiter.api.Assertions.*;
import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.*;
import org.loose.fis.sre.exceptions.*;
import org.loose.fis.sre.model.User;

import java.io.IOException;
import java.nio.file.FileSystemLoopException;

import static org.assertj.core.api.InstanceOfAssertFactories.BYTE;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.testfx.assertions.api.Assertions.assertThat;

class UserServiceTest {

    public static final String MANAGER = "manager";
    public static final String PATIENT = "patient";
    public static final String DOCTOR= "doctor";

    @AfterEach
    void tearDown() {
        System.out.println("After each");
    }

    @AfterAll
    static void afterAll() {
        System.out.println("After class");
    }

    @BeforeAll
    static void beforeAll() throws Exception{
        FileSystemService.APPLICATION_FOLDER = ".test-registration";
        FileSystemService.initDirectory();
        FileUtils.cleanDirectory(FileSystemService.getApplicationHomeFolder().toFile());
        UserService.initDatabase();
        System.out.println("Before class");
    }

    @BeforeEach
    void setUp() throws Exception {
        FileSystemService.APPLICATION_FOLDER = ".test-registration";
        FileSystemService.initDirectory();
        FileUtils.cleanDirectory(FileSystemService.getApplicationHomeFolder().toFile());
        UserService.initDatabase();
        System.out.println("Before each");
    }

    @Test
    @Order(1)
    @DisplayName("Database is initialized and there are no users")
    void testDataBaseIsInitializedAndNoUserIsPersisted() {
        assertThat(UserService.getAllUsers()).isNotNull();
        assertThat(UserService.getAllUsers()).isEmpty();
        System.out.println("1");
    }

    @Test
    @Order(2)
    @DisplayName("User is successfully persisted to Database")
    void testUserIsAddedToDatabase() throws UsernameAlreadyExistsException, InvalidCredentialException,InvalidEmailException {
        UserService.addUser(MANAGER, MANAGER, MANAGER,MANAGER,MANAGER, MANAGER,MANAGER,MANAGER);
        assertThat(UserService.getAllUsers()).isNotEmpty();
        assertThat(UserService.getAllUsers()).size().isEqualTo(1);
        User user = UserService.getAllUsers().get(0);
        assertThat(user).isNotNull();
        assertThat(user.getUsername()).isEqualTo(MANAGER);
        assertThat(user.getPassword()).isEqualTo(UserService.encodePassword(MANAGER, MANAGER));
        assertThat(user.getRole()).isEqualTo(MANAGER);
        System.out.println("2");
    }

    @Test
    @Order(3)
    @DisplayName("User can not be added twice")
    void testUserCanNotBeAddedTwice() {
        assertThrows(UsernameAlreadyExistsException.class, () -> {
            UserService.addUser(MANAGER, MANAGER, MANAGER,MANAGER,MANAGER, MANAGER,MANAGER,MANAGER);
            UserService.addUser(MANAGER, MANAGER, MANAGER,MANAGER,MANAGER, MANAGER,MANAGER,MANAGER);
        });
        System.out.println("3");
    }
    @Test
    @Order(4)
    @DisplayName("User can login")
    void testUserCanLogin() throws UsernameAlreadyExistsException, InvalidCredentialException,InvalidEmailException ,UsernameDoesNotExistException,WrongPasswordException,WrongRoleException{
        UserService.addUser(PATIENT, PATIENT, PATIENT,PATIENT,PATIENT, PATIENT,PATIENT,PATIENT);
        UserService.addUser(DOCTOR,DOCTOR,DOCTOR,DOCTOR,DOCTOR,DOCTOR,DOCTOR,DOCTOR);
        System.out.println("4");
    }
    @Test
    @Order(5)
    @DisplayName("Username must be unique")
    void testUserDoesNotAlreadyExist(){
        assertThrows(UsernameAlreadyExistsException.class, () -> {
            UserService.addUser(PATIENT, PATIENT, PATIENT,PATIENT,PATIENT, PATIENT,PATIENT,PATIENT);
            UserService.checkUserDoesNotAlreadyExist(PATIENT);
        });
        System.out.println("5");
    }
    @Test
    @Order(6)
    @DisplayName("User can not login with wrong credentials")
    void testUserCanNotLogin() {
        assertThrows(InvalidCredentialException.class, () -> {
            UserService.checkUserCredentials(PATIENT,DOCTOR,DOCTOR);
        });
        System.out.println("6");
    }
    @Test
    @Order(7)
    @DisplayName("Doctor does exist")
    void testAgentDoesExist() {
        assertThrows(DoctorDoesNotExistException.class, () -> {
            UserService.checkDoctorDoesExist("doctor");
        });
        System.out.println("7");
    }
    @Test
    @Order(8)
    @DisplayName("Username is correct")
    void testUsernameDoesExist() {
        assertThrows(IncorrectNameException.class, () -> {
            UserService.checkUsername("doctor");
        });
        System.out.println("8");
    }
    @Test
    @Order(9)
    @DisplayName("Name is correct")
    void testNameDoesExist() {
        assertThrows(IncorrectNameException.class, () -> {
            UserService.CheckNameCredentials("doctor");
        });
        System.out.println("9");
    }
}