package com.example.demo.Domain;

public class User implements DomainFacade {

    private int user_Id;
    private String e_mail;
    private String password;
    private String first_name;
    private String last_name;
    private int is_admin;

    public User() {
    }

    public User(int user_Id, String e_mail, String password, String first_name, String last_name, int is_admin) {
        this.user_Id = user_Id;
        this.e_mail = e_mail;
        this.password = password;
        this.first_name = first_name;
        this.last_name = last_name;
        this.is_admin = is_admin;
    }

    public int getUser_Id() {
        return user_Id;
    }

    public void setUser_Id(int user_Id) {
        this.user_Id = user_Id;
    }

    public String getE_mail() {
        return e_mail;
    }

    public void setE_mail(String e_mail) {
        this.e_mail = e_mail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public int getIs_admin() {
        return is_admin;
    }

    public void setIs_admin(int is_admin) {
        this.is_admin = is_admin;
    }

    @Override
    public String toString() {
        return "User{" +
                "user_Id=" + user_Id +
                ", e_mail='" + e_mail + '\'' +
                ", password='" + password + '\'' +
                ", first_name='" + first_name + '\'' +
                ", last_name='" + last_name + '\'' +
                ", is_admin=" + is_admin +
                '}';
    }
}
