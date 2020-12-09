package com.example.demo.Domain;

public class Participant implements DomainFacade {
    // FIELDS ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    private final int user_id;
    private final String first_name;
    private final String last_name;
    private final String e_mail;
    private final int project_id;
    private final int project_role_id;
    private final String project_role_name;

    // CONSTRUCTOR +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    public Participant(int user_id, String first_name, String last_name, String e_mail, int project_id, int project_role_id, String project_role_name) {
        this.user_id = user_id;
        this.first_name = first_name;
        this.last_name = last_name;
        this.e_mail = e_mail;
        this.project_id = project_id;
        this.project_role_id = project_role_id;
        this.project_role_name = project_role_name;
    }

    // BEHAVIOR ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    public int getUser_id() {
        return user_id;
    }

    public int getProject_id() {
        return project_id;
    }

    public int getProject_role_id() {
        return project_role_id;
    }

    public String getE_mail() {
        return e_mail;
    }

    public String getFirst_name() {
        return first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public String getProject_role_name() {
        return project_role_name;
    }
}
