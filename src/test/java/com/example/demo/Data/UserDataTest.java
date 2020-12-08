package com.example.demo.Data;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserDataTest {
    // FIELDS ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    private final UserData userData;

    // CONSTRUCTOR +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    public UserDataTest(ApplicationContext ctx) {
        this.userData = (UserData) ctx.getBean("userData");
    }

    // TEST ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    @BeforeEach
    void createTestUser() {
        // Every test requires a user object, we will make it for every test.
        userData.deleteUser();
        userData.createUser();
    }

    @AfterEach
    void deleteTestUser() {
        // After every test we will delete the test user.
    }

    @Test
    void getUsers() {
    }

    @Test
    void getUser() {
    }

    @Test
    void createUser() {
    }

    @Test
    void editUser() {
    }

    @Test
    void findUserIdFromEmail() {
    }

   @Test
    void findEmailFromUserId() throws SQLException {
        String e_mail = userData.findEmailFromUserId(2);
        assertEquals("dc@gk.dk", e_mail);
    }
}