/**
 * @author Adam and Rasmus
 */
package com.example.demo.Service;

import com.example.demo.Data.UserData;
import com.example.demo.Domain.User;
import org.springframework.web.multipart.MultipartFile;

import javax.sql.rowset.serial.SerialBlob;
import java.io.IOException;
import java.sql.Blob;
import java.sql.SQLException;
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

    public User login(String email, String password){
        User user = userData.login(email,password);
        return user;
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


    public void addProfilePicture(int user_id, MultipartFile file) {
        try {
            byte[] fileAsBytes = file.getBytes();
            Blob fileAsBlob = new SerialBlob(fileAsBytes);
            userData.uploadImg(user_id, fileAsBlob);
        } catch (IOException | SQLException ioException) {
            throw new NullPointerException(ioException.getMessage());
        }
    }
}
