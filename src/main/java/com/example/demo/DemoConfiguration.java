package com.example.demo;

import com.example.demo.Data.CustomerData;
import com.example.demo.Data.ProjectData;
import com.example.demo.Data.TaskData;
import com.example.demo.Data.UserData;
import com.example.demo.Mapper.ProjectMapper;
import com.example.demo.Mapper.TaskMapper;
import com.example.demo.Service.CustomerService;
import com.example.demo.Service.ProjectService;
import com.example.demo.Service.TaskService;
import com.example.demo.Service.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DemoConfiguration {

    private TaskMapper taskMapper;
    private TaskData taskData;
    private TaskService taskService;
    private CustomerData customerData;
    private CustomerService customerService;
    private UserData userData;
    private UserService userService;
    private ProjectMapper projectMapper;
    private ProjectData projectData;
    private ProjectService projectService;

    @Bean
    public TaskMapper taskMapper() {
        this.taskMapper = new TaskMapper();
        return taskMapper;
    }

    @Bean
    public TaskData taskData() {
        this.taskData = new TaskData();
        return taskData;
    }

    @Bean
    public TaskService taskService() {
        this.taskService = new TaskService();
        return taskService;
    }

    @Bean
    public CustomerData customerData() {
        this.customerData = new CustomerData();
        return customerData;
    }

    @Bean
    public CustomerService customerService() {
        this.customerService = new CustomerService();
        return customerService;
    }

    @Bean
    public UserData userData() {
        this.userData = new UserData();
        return userData;
    }

    @Bean
    public UserService userService() {
        this.userService = new UserService();
        return userService;
    }

    @Bean
    public ProjectMapper projectMapper() {
        this.projectMapper = new ProjectMapper();
        return projectMapper;
    }

    @Bean
    public ProjectData projectData() {
        this.projectData = new ProjectData(projectMapper);
        return projectData;
    }

    @Bean
    public ProjectService projectService() {
        this.projectService = new ProjectService(projectData, userData, customerData);
        return projectService;
    }
}
