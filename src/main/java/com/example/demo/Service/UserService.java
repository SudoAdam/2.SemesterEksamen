/**
 * @author Adam and Rasmus
 */
package com.example.demo.Service;

import com.example.demo.Data.UserData;
import com.example.demo.Domain.User;
import com.example.demo.Exceptions.ExecuteDeniedException;
import com.example.demo.Exceptions.LoginException;
import com.example.demo.Exceptions.QueryDeniedException;
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
    public UserService(UserData userData) {
        this.userData = userData;
    }

    // BEHAVIOR ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    public ArrayList<User> getUsers() throws QueryDeniedException {
        ArrayList<User> list = userData.getUsers();
        return list;
    }

    private String passwordHASH(String password) {
        String hash = "" + password.hashCode();
        return hash;
    }

    public User login(String email, String password) throws LoginException, QueryDeniedException {
        User user = userData.login(email, passwordHASH(password));
        return user;
    }

    public User getUser(int id) throws QueryDeniedException {
        return userData.getUser(id);
    }

    public void createUser(String e_mail, String password, String first_name, String last_name) throws ExecuteDeniedException {
        userData.createUser(e_mail, passwordHASH(password), first_name, last_name);
    }

    public void editUser(User user, int user_id, String e_mail, String pwd1, String pwd2, String oldPwd, String first_name, String last_name, int is_admin) throws SQLException {

        //to update Password
        String newPassword;
        oldPwd ="" + oldPwd.hashCode();
        if (pwd1.equals(pwd2) && oldPwd.equals(user.getPassword())) {
            newPassword ="" + pwd1.hashCode();
        } else {
            newPassword = user.getPassword();
        }

        userData.editUser(user_id, e_mail, newPassword, first_name, last_name, is_admin);
    }

    public int findUserIdFromEmail(String email) throws QueryDeniedException {
        return userData.findUserIdFromEmail(email);

    }

    public String findEmailFromUserId(int id) throws QueryDeniedException {
        return userData.findEmailFromUserId(id);
    }

    public void addProfilePicture(int user_id, MultipartFile file) throws ExecuteDeniedException {
        try {
            byte[] fileAsBytes = file.getBytes();
            Blob fileAsBlob = new SerialBlob(fileAsBytes);
            userData.uploadImg(user_id, fileAsBlob);
        } catch (IOException | SQLException ioException) {
            throw new NullPointerException(ioException.getMessage());
        }
    }

    public void deleteUser(int id) throws SQLException {
        userData.deleteUser(id);
    }

    public void setAdminStatus(int user_id, int is_admin) throws SQLException {
        userData.setAdminStatus(user_id, is_admin);
    }

    public void resetPassword(int user_id) throws SQLException {
        String passwordHash = "-1307671719";
        userData.setPassword(user_id,passwordHash);
    }
}
