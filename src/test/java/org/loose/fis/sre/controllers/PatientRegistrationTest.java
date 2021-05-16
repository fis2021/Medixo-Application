package org.loose.fis.sre.controllers;

import static org.junit.jupiter.api.Assertions.*;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.loose.fis.sre.services.FileSystemService;
import org.loose.fis.sre.services.UserService;
import org.testfx.api.FxRobot;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;

import static org.testfx.assertions.api.Assertions.assertThat;
@ExtendWith(ApplicationExtension.class)

class PatientRegistrationTest {

    public static final String ADMIN = "user";

    /*@BeforeAll
    static void beforeAll() throws Exception{
        FileSystemService.APPLICATION_FOLDER = ".test-registration-example";
        FileSystemService.initDirectory();
        FileUtils.cleanDirectory(FileSystemService.getApplicationHomeFolder().toFile());
        UserService.initDatabase();
        System.out.println("Before Class");
    }*/
    @BeforeEach
    void setUp() throws Exception {
        FileSystemService.APPLICATION_FOLDER = ".test-see-registration";
        FileSystemService.initDirectory();
        FileUtils.cleanDirectory(FileSystemService.getApplicationHomeFolder().toFile());
        UserService.initDatabase();
    }
    @Start
    void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("register_patient_user.fxml"));
        primaryStage.setTitle("Medixo Application");
        primaryStage.setScene(new Scene(root, 725, 490));
        primaryStage.show();
    }
    @Test
    void testRegistration(FxRobot robot) {

        robot.clickOn("#patientUsernameField");
        robot.write(ADMIN);
        robot.clickOn("#patientPasswordField");
        robot.write(ADMIN);
        robot.clickOn("#patientNameField");
        robot.write(ADMIN);
        robot.clickOn("#patientAgeField");
        robot.write(ADMIN);
        robot.clickOn("#patientEmailField");
        robot.write(ADMIN);
        robot.clickOn("#patientPhoneNumberField");
        robot.write(ADMIN);
        robot.clickOn("#registerButton");

        assertThat(robot.lookup("#registrationMessage").queryText()).hasText("Account created successfully!");
        assertThat(UserService.getAllUsers()).size().isEqualTo(1);

    }
}