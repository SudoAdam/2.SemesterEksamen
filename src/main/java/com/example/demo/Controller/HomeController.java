package com.example.demo.Controller;

import com.example.demo.Domain.User;
import com.example.demo.Service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.context.request.WebRequest;

import java.sql.SQLException;

@Controller
public class HomeController {
    UserService userService;

    public HomeController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/")
    public String showHome() throws Exception {
        return "authentication/login";
    }

    private void setSessionInfo(WebRequest request, User user) {
        // Place user info on session
        request.setAttribute("user", user, WebRequest.SCOPE_SESSION);
    }

    @PostMapping("/login")
    public String login(WebRequest request, Model model) throws SQLException {
        String email = request.getParameter("mail");
        String password = request.getParameter("password");
        User user = userService.login(email, password);
        setSessionInfo(request, user);
        return "redirect:/currentUser";
    }

    @GetMapping("/logout")
    public String logout(WebRequest request) {
        // en hurtig tanke... Ser ud til at virke. men skal testet godt igennem
        request.removeAttribute("user", WebRequest.SCOPE_SESSION);
        return "redirect:/";
    }

    @GetMapping("/loggedin")
    public String loggedin(WebRequest request, Model model) {
        User user = (User) request.getAttribute("user", WebRequest.SCOPE_SESSION);
        if (user != null) {
            return "authentication/loggedin";
        } else
            return "redirect:/";
    }


    @GetMapping("/createUser")
    public String createUser() {
        return "user/createUser";
    }

    @PostMapping("/createUser")
    public String createUser(WebRequest request) throws SQLException {
        String e_mail = request.getParameter("email");
        String password = request.getParameter("password");
        String first_name = request.getParameter("firstName");
        String last_name = request.getParameter("lastName");
        //vi skal have gjort så når man opretter sig, at den skriver direkte til databasen,
        // og så henter et bruger objekt tilbage fra databasen.
        // For så får vi nemlig userID med. med det samme.
        userService.createUser(e_mail, password, first_name, last_name);
        return "authentication/login";
    }

    @GetMapping("/forgot")
    public String forgot(){
        return "authentication/forgot";
    }
}

