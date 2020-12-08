package com.example.demo.Data;

import com.example.demo.Domain.User;
import com.example.demo.Exceptions.ExecutionDeniedException;
import com.example.demo.Exceptions.LoginException;
import com.example.demo.Exceptions.QueryDeniedException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

import java.sql.SQLException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserDataTest {
    // FIELDS ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    private final UserData userData;
    private int user_id;
    private final String e_mail;
    private final String password;
    private final String first_name;
    private final String last_name;

    // CONSTRUCTOR +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    public UserDataTest(ApplicationContext ctx) {
        this.userData = (UserData) ctx.getBean("userData");
        this.e_mail = "testing@mockville.com";
        this.password = "1234";
        this.first_name = "testy";
        this.last_name = "tester";
    }

    // BEHAVIOR ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    void assertUser(User user) {
        assertEquals(e_mail, user.getE_mail());
        assertEquals(password, user.getPassword());
        assertEquals(first_name, user.getFirst_name());
        assertEquals(last_name, user.getLast_name());
    }

    @BeforeEach
    void init() throws SQLException, QueryDeniedException, ExecutionDeniedException {
        // Every test requires a user object, we will make it for every test.
        userData.deleteUser(e_mail);
        userData.createUser(e_mail, password, first_name, last_name);
        user_id = userData.findUserIdFromEmail(e_mail);
        assertNotNull(user_id);
    }

    @AfterEach
    void destruct() throws SQLException {
        // After every test we will delete the test user.
        userData.deleteUser(e_mail);
        try {
            userData.findUserIdFromEmail(e_mail);
        } catch (QueryDeniedException e) {
            assertNotNull(e);
        }

    }

    // TEST ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    @Test
    void getUsers() throws QueryDeniedException {
        ArrayList<User> users = userData.getUsers();
        for (User u: users) {
            assertNotNull(u);
        }
    }

    @Test
    void getUser_byMail() throws QueryDeniedException {
        User user = userData.getUser(e_mail);
        assertUser(user);
    }

    @Test
    void getUser_byId() throws QueryDeniedException {
        User user = userData.getUser(user_id);
        assertUser(user);
    }

    @Test
    void findUserIdFromEmail() throws QueryDeniedException {
        int this_user_id = userData.findUserIdFromEmail(e_mail);
        assertEquals(user_id, this_user_id);
    }

    @Test
    void findEmailFromUserId() throws QueryDeniedException {
        String this_e_mail = userData.findEmailFromUserId(user_id);
        assertEquals(e_mail, this_e_mail);
    }

    @Test
    void login() throws LoginException, QueryDeniedException {
        User user = userData.login(e_mail, password);
        assertUser(user);
        assertThrows(LoginException.class, ()->{userData.login("false@123.com", "");} );
    }

    @Test
    void createUser_exception() {
        assertThrows(ExecutionDeniedException.class, ()->{userData.createUser("eeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee", "", "", "");});
    }

    @Test
    void editUser() {

    }
}