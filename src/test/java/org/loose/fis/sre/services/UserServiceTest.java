/*package org.loose.fis.sre.services;

import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.*;
import org.loose.fis.sre.services.FileSystemService;
import org.loose.fis.sre.services.UserService;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class UserServiceTest {
    @Test
    void testDemo() {
        System.out.println("test demo");
    }

    @BeforeEach
    void setUp() throws Exception {
        FileSystemService.APPLICATION_FOLDER = ".test-registration";
        FileUtils.cleanDirectory(FileSystemService.getApplicationHomeFolder().toFile());
    }

    @Test
    void testDataBaseIsInitializedAndNoUserIsPersisted() {
        UserService.initDatabase();
        assertThat(UserService.getAllUsers().isNotNull());
        assertThat(UserService.getAllUsers().isEmpty());
    }

    @Test
    void testUserIsAddedToDatabase() {
        UserService.initDatabase();
        UserService.addUser("");
    }
}*/