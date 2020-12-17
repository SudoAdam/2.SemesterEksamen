/**
 * Integration test of data layer
 *
 * @author Patrick Vincent Højstrøm
 * @since 9-12-2020
 */
package com.example.demo.Data;

import com.example.demo.Domain.User;
import com.example.demo.Exceptions.MapperExceptions.EmptyResultSetException;
import com.example.demo.Exceptions.DataExceptions.OperationDeniedException;
import com.example.demo.Exceptions.DataExceptions.QueryDeniedException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

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
    void construct() throws QueryDeniedException, OperationDeniedException {
        // Every test requires a user object, we will make it for every test.
        userData.deleteUser(e_mail);
        userData.createUser(e_mail, password, first_name, last_name);
        user_id = userData.findUserIdFromEmail(e_mail);
        assertNotNull(user_id);
    }

    @AfterEach
    void destruct() throws OperationDeniedException {
        // After every test we will delete the test user.
        userData.deleteUser(e_mail);
    }

    // TEST ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    @Test
    void createUser_failure() {
        assertThrows(OperationDeniedException.class, ()->{userData.createUser("eeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee", "", "", "");});
    }

    @Test
    void login_failure() {
        assertThrows(EmptyResultSetException.class, ()->{userData.login("false@123.com", "notARealPass");} );
    }

    @Test
    void getUser_failure() throws OperationDeniedException {
        userData.deleteUser(e_mail);
        assertThrows(EmptyResultSetException.class, ()->{userData.getUser(e_mail);});
    }

    @Test
    void getUsers_success() throws QueryDeniedException, EmptyResultSetException {
        ArrayList<User> users = userData.getUsers();
        for (User u: users) {
            assertNotNull(u);
        }
    }

    @Test
    void getUser_byMail_success() throws QueryDeniedException, EmptyResultSetException {
        User user = userData.getUser(e_mail);
        assertUser(user);
    }

    @Test
    void getUser_byID_success() throws QueryDeniedException, EmptyResultSetException {
        User user = userData.getUser(user_id);
        assertUser(user);
    }

    @Test
    void findUserIdFromEmail_success() throws QueryDeniedException {
        int this_user_id = userData.findUserIdFromEmail(e_mail);
        assertEquals(user_id, this_user_id);
    }

    @Test
    void findEmailFromUserId_success() throws QueryDeniedException {
        String this_e_mail = userData.findEmailFromUserId(user_id);
        assertEquals(e_mail, this_e_mail);
    }

    @Test
    void login_success() throws QueryDeniedException, EmptyResultSetException {
        User user = userData.login(e_mail, password);
        assertUser(user);
    }

    @Test
    void editUser_success() throws EmptyResultSetException, QueryDeniedException, OperationDeniedException {
        userData.editUser(user_id, e_mail, password, first_name, last_name, 1);
        User user = userData.getUser(user_id);
        assertEquals(1, user.getIs_admin());
    }
}