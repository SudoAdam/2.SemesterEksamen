package com.example.demo.Data;

import com.example.demo.Domain.Participant;
import com.example.demo.Domain.Project;
import com.example.demo.Exceptions.QueryDeniedException;
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
    public ArrayList<Participant> getProjectParticipants(int project_id) throws QueryDeniedException {
        try {
            Connection connection = connector.getConnection();
            String statement = "" +
                "SELECT p.user_id, u.first_name, u.last_name, u.e_mail, p.project_id, p.project_role_id, r.project_role" +
                "FROM project_participants p" +
                "LEFT JOIN users u" +
                "ON p.user_id = u.user_id" +
                "LEFT JOIN project_roles r" +
                "ON p.project_role_id = r.project_role_id" +
                "WHERE p.project_id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(statement);
            preparedStatement.setInt(1, project_id);
            ResultSet resultSet = preparedStatement.executeQuery();

            ArrayList<Participant> participants = new ArrayList<>();
            while (resultSet.next()) {
                participants.add((Participant) participantMapper.create(resultSet));
            }
            return participants;
        } catch (SQLException e) {
            throw new QueryDeniedException("Error when querying database: SQLException message: " + e.getMessage());
        }
    }

    public Participant getProjectParticipant(int user_id, int project_id) throws SQLException, QueryDeniedException {
        // Not working yet
        Connection connection = connector.getConnection();
        String statement = "SELECT * FROM project_participants WHERE user_id=? and project_id=?";
        PreparedStatement preparedStatement = connection.prepareStatement(statement);
        preparedStatement.setInt(1, user_id);
        preparedStatement.setInt(2, project_id);
        ResultSet resultSet = preparedStatement.executeQuery();
        return (Participant) participantMapper.create(resultSet);
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

    public void removeParticipant(int user_id, int project_id) throws SQLException {
        Connection connection = connector.getConnection();
        String statement = "DELETE FROM project_participants WHERE user_id=? and project_id=?";
        PreparedStatement preparedStatement = connection.prepareStatement(statement);
        preparedStatement.setInt(1, user_id);
        preparedStatement.setInt(2, project_id);
        preparedStatement.executeUpdate();
    }


    }

