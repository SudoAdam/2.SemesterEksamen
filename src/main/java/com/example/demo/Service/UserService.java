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
import java.util.ArrayList;

public class UserService {

    // FIELDS ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    private final UserData userData;

    // CONSTRUCTOR +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    public UserService(UserData userData){
        this.userData = userData;
    }

    // BEHAVIOR ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    public ArrayList<User> getUsers() throws SQLException{
        ArrayList<User> list = userData.getUsers();
        return list;
    }

    public User login(String email, String password) throws SQLException{
        User user = userData.login(email,password);
        return user;
    }

    public User getUser(int id) throws SQLException {
        return userData.getUser(id);
    }

    public void createUser(String e_mail, String password, String first_name, String last_name) throws SQLException{
        userData.createUser(e_mail, password, first_name, last_name);
    }

    public void editUser(int user_id, String e_mail, String password, String first_name, String last_name, int is_admin) throws SQLException{
        userData.editUser(user_id, e_mail, password, first_name, last_name, is_admin);
    }

    public int findUserIdFromEmail(String email) throws SQLException{
        return userData.findUserIdFromEmail(email);

    }

    public String findEmailFromUserId(int id) throws SQLException{
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

    public void deleteUser(int id) throws SQLException{
        userData.deleteUser(id);
    }
}
