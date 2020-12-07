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

    @GetMapping("/listUser")
    public String showUsers(Model model) throws SQLException{
        ArrayList<User> userList = userService.getUsers();
        model.addAttribute("userList", userList);
        return "user/listUser";
    }

    @GetMapping("/createUser")
    public String createUser() {
        return "user/createUser";
    }

    @PostMapping("/createUser")
    public String createUser(WebRequest request) throws SQLException{
        String e_mail = request.getParameter("e_mail");
        String password = request.getParameter("password");
        String first_name = request.getParameter("first_name");
        String last_name = request.getParameter("last_name");
        //vi skal have gjort så når man opretter sig, at den skriver direkte til databasen,
        // og så henter et bruger objekt tilbage fra databasen.
        // For så får vi nemlig userID med. med det samme.
        userService.createUser(e_mail, password, first_name, last_name);
        return "user/editUser";
    }

    @GetMapping("/editUser")
    public String editUser() {
        return "user/editUser";
    }


    @PostMapping("/updateUser")
    public String updateUser(WebRequest request, Model model) throws SQLException {
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


        // Mangler ordenlig password håndtering

        if (pwd1.equals(pwd2) && oldPwd.equals(user.getPassword())) {
            newPassword = pwd1;
        } else {
            newPassword = user.getPassword();
        }
        userService.editUser(uId, email, newPassword, first_name, last_name, isAdmin);

        return "user/currentUser";
    }

    @RequestMapping(value = "/viewUser", method = {RequestMethod.GET, RequestMethod.POST})
    public String viewUser(@RequestParam int id, Model model) throws SQLException {
        model.addAttribute("user", userService.getUser(id));
        return "user/viewUser";
    }

    @GetMapping("/currentUser")
    public String currentUser(Model model, WebRequest request) {
        return "user/currentUser";
    }

    @PostMapping("/uploadImg")
    public String uploadImg (@RequestParam("file") MultipartFile file, WebRequest request){
        User user = (User) request.getAttribute("user", WebRequest.SCOPE_SESSION);
        userService.addProfilePicture(user.getUser_id(), file);
        return "user/currentUser";
    }
}