/**
 * @author Adam and Rasmus
 */
package com.example.demo.Service;

import com.example.demo.Data.UserData;
import com.example.demo.Domain.User;
import com.example.demo.Exceptions.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;

public class UserService {
    // FIELDS ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    private final UserData userData;

    // CONSTRUCTOR +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    public UserService(UserData userData) {
        this.userData = userData;
    }

    // BEHAVIOR ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    public ArrayList<User> getUsers() throws FailedRequestException {
        try {
        ArrayList<User> list = userData.getUsers();
        return list;
        } catch (QueryDeniedException | EmptyResultSetException e) {
            throw new FailedRequestException(e.getMessage());
        }
    }

    private String passwordHASH(String password) {
        String hash = "" + password.hashCode();
        return hash;
    }

    public User login(String email, String password) throws FailedRequestException, LoginException {
        try {
            User user = userData.login(email, passwordHASH(password));
            return user;
        } catch (QueryDeniedException e) {
            throw new FailedRequestException(e.getMessage());
        } catch (EmptyResultSetException e) {
            throw new LoginException();
        }
    }

    public User getUser(int id) throws FailedRequestException {
        try {
            return userData.getUser(id);
        } catch (QueryDeniedException | EmptyResultSetException e) {
            throw new FailedRequestException(e.getMessage());
        }
    }

    public void createUser(String e_mail, String password, String first_name, String last_name) throws FailedRequestException {
        try {
            userData.createUser(e_mail, passwordHASH(password), first_name, last_name);
        } catch (ExecuteDeniedException e) {
            throw new FailedRequestException(e.getMessage());
        }
    }

    public void editUser(User user, int user_id, String e_mail, String pwd1, String pwd2, String oldPwd, String first_name, String last_name, int is_admin) throws FailedRequestException {
        try {
            //to update Password
            String newPassword;
            oldPwd = "" + oldPwd.hashCode();
            if (pwd1.equals(pwd2) && oldPwd.equals(user.getPassword())) {
                newPassword = "" + pwd1.hashCode();
            } else {
                newPassword = user.getPassword();
            }

            userData.editUser(user_id, e_mail, newPassword, first_name, last_name, is_admin);
        } catch (ExecuteDeniedException e) {
            throw new FailedRequestException(e.getMessage());
        }
    }

    public int findUserIdFromEmail(String email) throws FailedRequestException {
        try {
            return userData.findUserIdFromEmail(email);
        } catch (QueryDeniedException e) {
            throw new FailedRequestException(e.getMessage());
        }
    }

    public String findEmailFromUserId(int id) throws FailedRequestException {
        try {
            return userData.findEmailFromUserId(id);
        } catch (QueryDeniedException e) {
            throw new FailedRequestException(e.getMessage());
        }
    }

    public void addProfilePicture(int user_id, MultipartFile file) throws FailedRequestException {
        try {
            byte[] fileAsBytes = file.getBytes();
            userData.uploadImg(user_id, fileAsBytes);
        } catch (IOException | ExecuteDeniedException e) {
            throw new FailedRequestException(e.getMessage());
        }
    }

    public void deleteUser(int id) throws FailedRequestException {
        try {
            userData.deleteUser(id);
        } catch (ExecuteDeniedException e) {
            throw new FailedRequestException(e.getMessage());
        }
    }

    public void setAdminStatus(int user_id, int is_admin) throws FailedRequestException {
        try {
            userData.setAdminStatus(user_id, is_admin);
        } catch (ExecuteDeniedException e) {
            throw new FailedRequestException(e.getMessage());
        }
    }

    public void resetPassword(int user_id) throws FailedRequestException {
        try {
            String passwordHash = "-1307671719";
            userData.setPassword(user_id, passwordHash);
        } catch (ExecuteDeniedException e) {
            throw new FailedRequestException(e.getMessage());
        }
    }
}
