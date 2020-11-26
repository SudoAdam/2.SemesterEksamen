package com.example.demo.Domain;

public class Project {
    private String projectName;
    private int startDate;
    private int endDate;
    private int employeeNumber;

    public Project(String projectName, int startDate, int endDate, int employeeNumber) {
        this.projectName = projectName;
        this.startDate = startDate;
        this.endDate = endDate;
        this.employeeNumber = employeeNumber;
    }

    public Project() {
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public int getStartDate() {
        return startDate;
    }

    public void setStartDate(int startDate) {
        this.startDate = startDate;
    }

    public int getEndDate() {
        return endDate;
    }

    public void setEndDate(int endDate) {
        this.endDate = endDate;
    }

    public int getEmployeeNumber() {
        return employeeNumber;
    }

    public void setEmployeeNumber(int employeeNumber) {
        this.employeeNumber = employeeNumber;
    }

    @Override
    public String toString() {
        return "Project{" +
                "projectName='" + projectName + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", employeeNumber=" + employeeNumber +
                '}';
    }
}
