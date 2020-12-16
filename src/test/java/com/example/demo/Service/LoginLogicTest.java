/**
 * Unit test of login logic
 *
 * @author Patrick Vincent Højstrøm
 * @since 11-12-2020
 */
package com.example.demo.Service;

import com.example.demo.Domain.User;
import org.junit.jupiter.api.Test;
import org.springframework.web.context.request.WebRequest;

import java.security.Principal;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class LoginLogicTest {
    private final LoginLogic loginLogic;
    private final WebRequest webRequest;

    LoginLogicTest() {
        this.loginLogic = new LoginLogic();
        this.webRequest = new WebRequest() {
            private final Map<String, Object> attributes = new HashMap<>();
            private final Map<Integer, Map<String, Object>> session = new HashMap<>();

            @Override
            public String getHeader(String s) {
                return null;
            }

            @Override
            public String[] getHeaderValues(String s) {
                return new String[0];
            }

            @Override
            public Iterator<String> getHeaderNames() {
                return null;
            }

            @Override
            public String getParameter(String s) {
                return null;
            }

            @Override
            public String[] getParameterValues(String s) {
                return new String[0];
            }

            @Override
            public Iterator<String> getParameterNames() {
                return null;
            }

            @Override
            public Map<String, String[]> getParameterMap() {
                return null;
            }

            @Override
            public Locale getLocale() {
                return null;
            }

            @Override
            public String getContextPath() {
                return null;
            }

            @Override
            public String getRemoteUser() {
                return null;
            }

            @Override
            public Principal getUserPrincipal() {
                return null;
            }

            @Override
            public boolean isUserInRole(String s) {
                return false;
            }

            @Override
            public boolean isSecure() {
                return false;
            }

            @Override
            public boolean checkNotModified(long l) {
                return false;
            }

            @Override
            public boolean checkNotModified(String s) {
                return false;
            }

            @Override
            public boolean checkNotModified(String s, long l) {
                return false;
            }

            @Override
            public String getDescription(boolean b) {
                return null;
            }

            @Override
            public Object getAttribute(String s, int i) {
                return session.get(i).get(s);
            }

            @Override
            public void setAttribute(String s, Object o, int i) {
                attributes.put(s, o);
                session.put(i, attributes);
            }

            @Override
            public void removeAttribute(String s, int i) {
                attributes.remove(s);
            }

            @Override
            public String[] getAttributeNames(int i) {
                return new String[0];
            }

            @Override
            public void registerDestructionCallback(String s, Runnable runnable, int i) {

            }

            @Override
            public Object resolveReference(String s) {
                return null;
            }

            @Override
            public String getSessionId() {
                return null;
            }

            @Override
            public Object getSessionMutex() {
                return null;
            }
        };
    }

    @Test
    void checkLogin() {
        webRequest.setAttribute("user", new User(), WebRequest.SCOPE_SESSION);
        assertTrue(loginLogic.checkLogin(webRequest));

        webRequest.removeAttribute("user", webRequest.SCOPE_SESSION);
        assertFalse(loginLogic.checkLogin(webRequest));
    }
}