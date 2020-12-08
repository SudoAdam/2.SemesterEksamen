/**
 * @Author Rasmus Berg and Adam
 */

package com.example.demo.Controller;

import com.example.demo.Domain.User;
import com.example.demo.Exceptions.ExecuteDeniedException;
import com.example.demo.Exceptions.QueryDeniedException;
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
    public String showUsers(Model model) throws SQLException, QueryDeniedException {
        ArrayList<User> userList = userService.getUsers();
        model.addAttribute("userList", userList);
        return "user/listUser";
    }

    @GetMapping("/editUser")
    public String editUser() {
        return "user/editUser";
    }


    @PostMapping("/updateUser")
    public String updateUser(WebRequest request) throws SQLException, QueryDeniedException {
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

        User updatedUser = userService.getUser(uId);
        setSessionInfo(request, updatedUser);
        return "redirect:/currentUser";
    }

    @RequestMapping(value = "/viewUser", method = {RequestMethod.GET, RequestMethod.POST})
    public String viewUser(@RequestParam int id, Model model) throws QueryDeniedException {
        model.addAttribute("user", userService.getUser(id));
        return "user/viewUser";
    }

    @GetMapping("/currentUser")
    public String currentUser() {
        return "user/currentUser";
    }

    @PostMapping("/uploadImg")
    public String uploadImg(@RequestParam("file") MultipartFile file, WebRequest request) throws QueryDeniedException, ExecuteDeniedException {
        User user = (User) request.getAttribute("user", WebRequest.SCOPE_SESSION);
        userService.addProfilePicture(user.getUser_id(), file);
        User updatedUser = userService.getUser(user.getUser_id());
        setSessionInfo(request, updatedUser);
        return "redirect:/currentUser";
    }

    @RequestMapping(value = "/deleteUser", method = {RequestMethod.GET, RequestMethod.POST})
    public String deleteUser(@RequestParam int id) throws SQLException {
        userService.deleteUser(id);
        return "redirect:/listUser";
    }
}