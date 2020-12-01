/**
 * @author Adam
 */
package com.example.demo.Service;

import com.example.demo.Data.UserData;

import java.time.LocalDate;

public class UserService {

    // FIELDS ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    private final UserData userData;

    // CONSTRUCTOR +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    public UserService(){
        this.userData = new UserData();
    }

    // BEHAVIOR ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

    public boolean createUser(String e_mail, String password, String first_name, String last_name, int job_title_id){
        return userData.createUser(e_mail, password, first_name, last_name, job_title_id);
    }

    public boolean editUser(int user_id, String e_mail, String password, String first_name, String last_name, int is_admin, int job_title_id) {
        return userData.editUser(user_id, e_mail, password, first_name, last_name, is_admin, job_title_id);
    }
}
