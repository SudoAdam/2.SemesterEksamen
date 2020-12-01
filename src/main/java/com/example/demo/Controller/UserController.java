//@author Adam
package com.example.demo.Controller;

import com.example.demo.Service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.context.request.WebRequest;

@Controller
public class UserController {


    @GetMapping("/createUser")
    public String createUser() { return "user/createUser";}

    @PostMapping("/createUser")
    public String createUser(WebRequest request) {
        String e_mail = request.getParameter("e_mail");
        String password = request.getParameter("password");
        String first_name = request.getParameter("first_name");
        String last_name = request.getParameter("last_name");
        String job_title_id = request.getParameter("job_title_id");
        int job_title_id_int = Integer.parseInt(job_title_id);
        //vi skal have gjort så når man opretter sig, at den skriver direkte til databasen,
        // og så henter et bruger objekt tilbage fra databasen.
        // For så får vi nemlig userID med. med det samme.
        UserService userService = new UserService();
        userService.createUser(e_mail, password, first_name, last_name, job_title_id_int);
        return "user/editUser";
    }

    @GetMapping("/listUser")
    public String showUsers() {
        return "user/listUser";
    }

    @GetMapping("/editUser")
    public String editUser() {
        return "user/editUser";
    }
}