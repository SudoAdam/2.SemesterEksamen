package com.example.demo.Domain;

import java.util.Date;

public class Project implements DomainFacade {
    private int project_id;
    private String name;
    private Date kickoff;
    private Date deadline;
    private int project_leader_id;
    private int customer_id;

    public Project(int project_id, String name, Date kickoff, Date deadline, int project_leader_id, int customer_id) {
        this.project_id = project_id;
        this.name = name;
        this.kickoff = kickoff;
        this.deadline = deadline;
        this.project_leader_id = project_leader_id;
        this.customer_id = customer_id;
    }

    public int getProject_id() {
        return project_id;
    }

    public String getName() {
        return name;
    }

    public Date getKickoff() {
        return kickoff;
    }

    public Date getDeadline() {
        return deadline;
    }

    public int getProject_leader_id() {
        return project_leader_id;
    }

    public int getCustomer_id() {
        return customer_id;
    }
}
