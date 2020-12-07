package com.example.demo.Data;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserDataTest {

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
    void findEmailFromUserId(ApplicationContext ctx) throws SQLException {
        UserData userData = (UserData) ctx.getBean("userData");
        String e_mail = userData.findEmailFromUserId(2);
        assertEquals("dc@gk.dk", e_mail);
    }
}