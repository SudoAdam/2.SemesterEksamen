/*
* @Auther everyone
* */package com.example.demo.Domain;

import java.time.LocalDate;

public class Project implements DomainInterface {
    // FIELDS ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    private final int project_id;
    private final String project_name;
    private final LocalDate kickoff;
    private final LocalDate deadline;
    private final int project_leader_id;
    private final int customer_id;
    private User project_leader;
    private Customer customer;

    // CONSTRUCTOR +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    public Project(int project_id, String project_name, LocalDate kickoff, LocalDate deadline, int project_leader_id, int customer_id) {
        this.project_id = project_id;
        this.project_name = project_name;
        this.kickoff = kickoff;
        this.deadline = deadline;
        this.project_leader_id = project_leader_id;
        this.customer_id = customer_id;
    }
    // BEHAVIOR ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    public User getProject_leader() {
        return project_leader;
    }

    public void setProject_leader(User project_leader) {
        this.project_leader = project_leader;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public int getProject_id() {
        return project_id;
    }

    public String getProject_name() {
        return project_name;
    }

    public LocalDate getKickoff() {
        return kickoff;
    }

    public LocalDate getDeadline() {
        return deadline;
    }

    public int getProject_leader_id() {
        return project_leader_id;
    }

    public int getCustomer_id() {
        return customer_id;
    }
}
