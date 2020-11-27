package com.example.demo.Domain;

public class User {

    private int userId;
    private String firstName;
    private String lastName;
    private String email;
    private String projects;
    private int isAdmin;
    private String jobTitle;

    public User(int userId, String firstName, String lastName, String email, String projects, int isAdmin, String jobTitle) {
        this.userId = userId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.projects = projects;
        this.isAdmin = isAdmin;
        this.jobTitle = jobTitle;
    }

    public User() {
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getProjects() {
        return projects;
    }

    public void setProjects(String projects) {
        this.projects = projects;
    }

    public int getIsAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(int isAdmin) {
        this.isAdmin = isAdmin;
    }

    public String getJobTitel() {
        return jobTitle;
    }

    public void setJobTitel(String jobTitel) {
        this.jobTitle = jobTitel;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", projects='" + projects + '\'' +
                ", isAdmin='" + isAdmin + '\'' +
                ", jobTitel='" + jobTitle + '\'' +
                '}';
    }
}
