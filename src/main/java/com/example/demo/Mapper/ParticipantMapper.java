package com.example.demo.Mapper;

import com.example.demo.Domain.Participant;

import java.sql.ResultSet;
import java.sql.SQLException;


public class ParticipantMapper extends AbstractMapper {
    // BEHAVIOR ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    @Override
    public Participant mapping(ResultSet resultSet) throws SQLException {
        int user_id = resultSet.getInt("user_id");
        int project_id = resultSet.getInt("project_id");
        int project_role_id = resultSet.getInt("project_role_id");

        return new Participant(user_id,project_id,project_role_id);
    }
}
