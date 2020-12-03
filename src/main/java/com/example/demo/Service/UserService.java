/**
 * @author Adam
 */
package com.example.demo.Service;

import com.example.demo.Data.UserData;
import com.example.demo.Domain.User;

import java.time.LocalDate;
import java.util.ArrayList;

public class UserService {

    // FIELDS ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    private final UserData userData;

    // CONSTRUCTOR +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    public UserService(){
        this.userData = new UserData();
    }

    // BEHAVIOR ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    public ArrayList<User> getUsers(){
        ArrayList<User> list = userData.getUsers();
        return list;
    }

    public User getUser(int id){
        return userData.getUser(id);
    }

    public boolean createUser(String e_mail, String password, String first_name, String last_name){
        return userData.createUser(e_mail, password, first_name, last_name);
    }

    public boolean editUser(int user_id, String e_mail, String password, String first_name, String last_name, int is_admin) {
        return userData.editUser(user_id, e_mail, password, first_name, last_name, is_admin);
    }

    public int findUserIdFromEmail(String email){
        return userData.findUserIdFromEmail(email);

    }

    public String findEmailFromUserId(int id){
        return userData.findEmailFromUserId(id);
    }
}
