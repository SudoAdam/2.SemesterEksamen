/**
 * @Author Rasmus Berg and Adam
 */

package com.example.demo.Controller;

import com.example.demo.Domain.User;
import com.example.demo.Service.ProjectService;
import com.example.demo.Service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.multipart.MultipartFile;

import java.sql.SQLException;
import java.util.ArrayList;

@Controller
public class UserController {
    UserService userService;
    ProjectService projectService;

    public UserController(UserService userService, ProjectService projectService) {
        this.userService = userService;
        this.projectService = projectService;
    }

    private void setSessionInfo(WebRequest request, User user) {
        // Place user info on session
        request.setAttribute("user", user, WebRequest.SCOPE_SESSION);
    }

    @GetMapping("/listUser")
    public String showUsers(Model model) throws SQLException {
        ArrayList<User> userList = userService.getUsers();
        model.addAttribute("userList", userList);
        return "user/listUser";
    }

    @GetMapping("/editUser")
    public String editUser() {
        return "user/editUser";
    }


    @PostMapping("/updateUser")
    public String updateUser(WebRequest request) throws SQLException {
        User user = (User) request.getAttribute("user", WebRequest.SCOPE_SESSION);
        String userId = request.getParameter("uId");
        String first_name = request.getParameter("fName");
        String last_name = request.getParameter("lName");
        String email = request.getParameter("email");
        String oldPwd = request.getParameter("oPwd");
        String pwd1 = request.getParameter("pwd1");
        String pwd2 = request.getParameter("pwd2");
        int uId = Integer.parseInt(userId);
        String newPassword;
        int isAdmin = user.getIs_admin();

        userService.editUser(user, uId, email, pwd1, pwd2, oldPwd, first_name, last_name, isAdmin);

        User updatedUser = userService.getUser(uId);
        setSessionInfo(request, updatedUser);
        return "redirect:/currentUser";
    }

    @RequestMapping(value = "/viewUser", method = {RequestMethod.GET, RequestMethod.POST})
    public String viewUser(@RequestParam int id, Model model) throws SQLException {
        model.addAttribute("user", userService.getUser(id));
        return "user/viewUser";
    }

    @GetMapping("/currentUser")
    public String currentUser() {
        return "user/currentUser";
    }

    @PostMapping("/uploadImg")
    public String uploadImg(@RequestParam("file") MultipartFile file, WebRequest request) throws SQLException {
        User user = (User) request.getAttribute("user", WebRequest.SCOPE_SESSION);
        userService.addProfilePicture(user.getUser_id(), file);
        User updatedUser = userService.getUser(user.getUser_id());
        setSessionInfo(request, updatedUser);
        return "redirect:/currentUser";
    }

    @PostMapping("/updateUserRole")
    public String updateUserRole(WebRequest request) throws SQLException {
        String userId = request.getParameter("user_id");
        String isAdmin = request.getParameter("is_admin");
        if (isAdmin == null) {
            isAdmin = "0";
        }
        int user_id = Integer.parseInt(userId);
        int is_admin = Integer.parseInt(isAdmin);
        userService.setAdminStatus(user_id, is_admin);
        return "redirect:/listUser";
    }


    @RequestMapping(value = "/deleteUser", method = {RequestMethod.GET, RequestMethod.POST})
    public String deleteUser(@RequestParam int id) throws SQLException {
        userService.deleteUser(id);
        return "redirect:/listUser";
    }
}