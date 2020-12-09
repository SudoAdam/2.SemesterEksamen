package com.example.demo.Domain;

public class Participant implements DomainFacade{
    // FIELDS ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    private int user_id;
    private int project_id;
    private int project_role_id;
    private User participant_user;

    // CONSTRUCTOR +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    public Participant(int user_id, int project_id, int project_role_id) {
        this.user_id = user_id;
        this.project_id = project_id;
        this.project_role_id = project_role_id;

    }

    // BEHAVIOR ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getProject_id() {
        return project_id;
    }

    public void setProject_id(int project_id) {
        this.project_id = project_id;
    }

    public int getProject_role_id() {
        return project_role_id;
    }

    public void setProject_role_id(int project_role_id) {
        this.project_role_id = project_role_id;
    }

    public User getParticipant_user() {
        return participant_user;
    }

    public void setParticipant_user(User participant_user) {
        this.participant_user = participant_user;
    }
}
