package com.example.demo.Data;

import com.example.demo.Domain.Participant;
import com.example.demo.Domain.Project;
import com.example.demo.Mapper.ParticipantMapper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ParticipantData {
    // FIELDS ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    private final ParticipantMapper participantMapper;
    private final Connector connector;
    // CONSTRUCTOR +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    public ParticipantData(ParticipantMapper participantMapper, Connector connector) {
        this.participantMapper = participantMapper;
        this.connector = connector;
    }
    // BEHAVIOR ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    public ArrayList<Participant> getParticipants() throws SQLException {
        Connection connection = connector.getConnection();
        String statement = "SELECT * FROM project_participants";
        PreparedStatement preparedStatement = connection.prepareStatement(statement);
        ResultSet resultSet = preparedStatement.executeQuery();

        ArrayList<Participant> participants = new ArrayList<>();
        while (resultSet.next()) {
            participants.add((Participant) participantMapper.create(resultSet));
        }
        return participants;
    }

    public void assignUserToProject(int user_id, int project_id, int project_role_id) throws SQLException {
        Connection connection = connector.getConnection();
        String statement = "INSERT INTO project_participants (user_id, project_id, project_role_id) VALUES (?,?,?)";
        PreparedStatement preparedStatement = connection.prepareStatement(statement);
        preparedStatement.setInt(1, user_id);
        preparedStatement.setInt(2, project_id);
        preparedStatement.setInt(3, project_role_id);
        preparedStatement.execute();
    }
    }

