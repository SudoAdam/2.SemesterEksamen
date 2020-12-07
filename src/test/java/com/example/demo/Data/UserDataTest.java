package com.example.demo.Data;

import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class UserDataTest {

    UserData userData = new UserData();

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