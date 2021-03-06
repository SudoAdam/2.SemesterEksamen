/**
 * @author Adam, Rasmus
 * @version 1.0
 * @since 1-11-2020
 */
package com.example.demo.Service;

import com.example.demo.Data.UserData;
import com.example.demo.Domain.User;
import com.example.demo.Exceptions.MapperExceptions.EmptyResultSetException;
import com.example.demo.Exceptions.DataExceptions.OperationDeniedException;
import com.example.demo.Exceptions.DataExceptions.QueryDeniedException;
import com.example.demo.Exceptions.ServiceExceptions.FailedRequestException;
import com.example.demo.Exceptions.ServiceExceptions.LoginException;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;

public class UserService {
    // FIELDS ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    private final UserData userData;
    private final EncryptionLogic encryptionLogic;

    // CONSTRUCTOR +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    public UserService(UserData userData, EncryptionLogic encryptionLogic) {
        this.userData = userData;
        this.encryptionLogic = encryptionLogic;
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

    public User login(String email, String password) throws FailedRequestException, LoginException {
        try {
            String encrypted = encryptionLogic.toHash(password);
            User user = userData.login(email, encrypted);
            return user;
        } catch (QueryDeniedException e) {
            throw new FailedRequestException(e.getMessage());
        } catch (EmptyResultSetException e) {
            throw new LoginException("Incorrect credentials was given...");
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
            String encrypted = encryptionLogic.toHash(password);
            userData.createUser(e_mail, encrypted, first_name, last_name);
        } catch (OperationDeniedException e) {
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
        } catch (OperationDeniedException e) {
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
        } catch (IOException | OperationDeniedException e) {
            throw new FailedRequestException(e.getMessage());
        }
    }

    public void deleteUser(int id) throws FailedRequestException {
        try {
            userData.deleteUser(id);
        } catch (OperationDeniedException e) {
            throw new FailedRequestException(e.getMessage());
        }
    }

    public void setAdminStatus(int user_id, int is_admin) throws FailedRequestException {
        try {
            userData.setAdminStatus(user_id, is_admin);
        } catch (OperationDeniedException e) {
            throw new FailedRequestException(e.getMessage());
        }
    }

    public void resetPassword(int user_id) throws FailedRequestException {
        try {
            String passwordHash = "-1307671719";
            userData.setPassword(user_id, passwordHash);
        } catch (OperationDeniedException e) {
            throw new FailedRequestException(e.getMessage());
        }
    }
}
