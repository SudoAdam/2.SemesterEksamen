/**
 * @Author Adam
 */
package com.example.demo.Service;

import org.springframework.web.context.request.WebRequest;

public class LoginLogic {

    public Boolean checkLogin(WebRequest request) {
        if (request.getAttribute("user", WebRequest.SCOPE_SESSION) == null) {
            return false;
        } else {
            return true;
        }
    }
}
