/**
 * @author Adam
 */
package com.example.demo.Service;

import com.example.demo.Data.UserData;

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
}
