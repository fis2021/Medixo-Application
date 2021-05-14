package org.loose.fis.sre.services;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserServiceTest {
    @Test
    void testDemo() {
        System.out.println("test demo");
    }

    @Test
    void testDataBaseIsInitializedAndNoUserIsPersisted() {
        UserService.initDatabase();
        assertThat();
        UserService.getAllUsers()
    }
}