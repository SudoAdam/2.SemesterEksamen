//@author Adam
package com.example.demo.Controller;

import com.example.demo.Service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.context.request.WebRequest;

@Controller
public class RegisterController {

    @PostMapping("/register")
    public String registerUser(WebRequest request) {
        String email = request.getParameter("email");
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String jobTitle = request.getParameter("jobTitle");
        //vi skal have gjort så når man opretter sig, at den skriver direkte til databasen,
        // og så henter et bruger objekt tilbage fra databasen.
        // For så får vi nemlig userID med. med det samme.
        UserService userService = new UserService();
        userService.createUser(email,firstName,lastName,jobTitle);
        return "edit/employeeEdit";
    }
}