/**
 @Author Rasmus Berg and Adam
 */

package com.example.demo.Controller;


import com.example.demo.Domain.User;
import com.example.demo.Service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import java.util.ArrayList;

@Controller
public class UserController {
    UserService userService = new UserService();

    @GetMapping("/listUser")
    public String showUsers(Model model) {
        ArrayList<User> userList = userService.getUsers();
        model.addAttribute("userList", userList);
        return "user/listUser";
    }

    @GetMapping("/createUser")
    public String createUser() { return "user/createUser";}

    @PostMapping("/createUser")
    public String createUser(WebRequest request) {
        String e_mail = request.getParameter("e_mail");
        String password = request.getParameter("password");
        String first_name = request.getParameter("first_name");
        String last_name = request.getParameter("last_name");
        //vi skal have gjort så når man opretter sig, at den skriver direkte til databasen,
        // og så henter et bruger objekt tilbage fra databasen.
        // For så får vi nemlig userID med. med det samme.
        UserService userService = new UserService();
        userService.createUser(e_mail, password, first_name, last_name);
        return "user/editUser";
    }

    @GetMapping("/editUser")
    public String editUser() {
        return "user/editUser";
    }

    @RequestMapping(value = "/viewUser", method = {RequestMethod.GET, RequestMethod.POST})
    public String viewProject(@RequestParam int id, Model model) {
        model.addAttribute("user", userService.getUser(id));
        return "user/viewUser";
    }
}
