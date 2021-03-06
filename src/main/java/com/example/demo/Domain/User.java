/**
 * @Author Rasmus Berg
 */


package com.example.demo.Domain;

import org.apache.tomcat.util.codec.binary.Base64;

public class User implements DomainInterface {
    // FIELDS ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    private int user_id;
    private String e_mail;
    private String password;
    private String first_name;
    private String last_name;
    private int is_admin;
    private byte[] img;

    public User() {
    }
    // CONSTRUCTOR +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    public User(int user_id, String e_mail, String password, String first_name, String last_name, int is_admin, byte[] img) {
        this.user_id = user_id;
        this.e_mail = e_mail;
        this.password = password;
        this.first_name = first_name;
        this.last_name = last_name;
        this.is_admin = is_admin;
        this.img = img;
    }
    // BEHAVIOR ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
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

    public String byteArrayAs64String() {
        return Base64.encodeBase64String(this.img);
    }

    @Override
    public String toString() {
        return "User{" +
                "user_Id=" + user_id +
                ", e_mail='" + e_mail + '\'' +
                ", password='" + password + '\'' +
                ", first_name='" + first_name + '\'' +
                ", last_name='" + last_name + '\'' +
                ", is_admin=" + is_admin +
                '}';
    }
}
