/**
 * @author Patrick Vincent Højstrøm
 * @version 1.0
 * @since 07-12-2020
 */
package com.example.demo;

import com.example.demo.Data.*;
import com.example.demo.Mapper.*;
import com.example.demo.Service.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DemoConfiguration {

    @Bean
    public LoginLogic loginLogic() {
        return new LoginLogic();
    }

    @Bean
    public Connector connector() {
        return new Connector();
    }

    @Bean
    public DateLogic dateLogic() { return new DateLogic(); }

    @Bean
    public EncryptionLogic encryptionLogic() { return new EncryptionLogic(); }

    @Bean
    public SubTaskMapper subTaskMapper() { return new SubTaskMapper(); }

    @Bean
    public SubTaskData subTaskData(SubTaskMapper subTaskMapper, Connector connector) {
        return new SubTaskData(subTaskMapper, connector);
    }

    @Bean
    public TaskMapper taskMapper() {
        return new TaskMapper();
    }

    @Bean
    public TaskData taskData(TaskMapper taskMapper, Connector connector) {
        return new TaskData(taskMapper, connector);
    }

    @Bean
    public TaskService taskService(UserService userService, TaskData taskData, SubTaskData subTaskData, DateLogic dateLogic) {
        return new TaskService(userService, taskData, subTaskData, dateLogic);
    }

    @Bean
    public CustomerMapper customerMapper() {
        return new CustomerMapper();
    }

    @Bean
    public CustomerData customerData(CustomerMapper customerMapper, Connector connector) {
        return new CustomerData(customerMapper, connector);
    }

    @Bean
    public CustomerService customerService(CustomerData customerData) {
        return new CustomerService(customerData);
    }

    @Bean
    public UserMapper userMapper() {
        return new UserMapper();
    }

    @Bean
    public UserData userData(UserMapper userMapper, Connector connector) {
        return new UserData(userMapper, connector);
    }

    @Bean
    public UserService userService(UserData userData, EncryptionLogic encryptionLogic) {
        return new UserService(userData, encryptionLogic);
    }

    @Bean
    public ParticipantMapper participantMapper() {return new ParticipantMapper();}

    @Bean
    public ParticipantData participantData(ParticipantMapper participantMapper, Connector connector) {return new ParticipantData(participantMapper, connector);}

    @Bean
    public ProjectMapper projectMapper() {
        return new ProjectMapper();
    }

    @Bean
    public ProjectData projectData(ProjectMapper projectMapper) {
        return new ProjectData(projectMapper);
    }

    @Bean
    public ProjectService projectService(ProjectData projectData, UserData userData, CustomerData customerData, ParticipantData participantData, DateLogic dateLogic) {
        return new ProjectService(projectData, userData, customerData, participantData, dateLogic);
    }
}
