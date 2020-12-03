package com.example.demo.Controller;

import com.example.demo.Exceptions.ApiException;
import com.example.demo.Exceptions.ApiRequestException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String showHome() {
        return "authentication/login";
    }

    @GetMapping("/apiError")
    public String apiError() throws ApiRequestException {
        return "apiError";
    }

}

