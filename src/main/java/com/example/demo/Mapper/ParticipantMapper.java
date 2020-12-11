package com.example.demo.Mapper;

import com.example.demo.Domain.Participant;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ParticipantMapper extends AbstractMapper {
    // BEHAVIOR ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    @Override
    public Participant mapping(ResultSet resultSet) throws SQLException {
        int user_id = resultSet.getInt("user_id");
        String first_name = resultSet.getString("first_name");
        String last_name = resultSet.getString("last_name");
        String e_mail = resultSet.getString("e_mail");
        int project_id = resultSet.getInt("project_id");
        int project_role_id = resultSet.getInt("project_role_id");
        String project_role = resultSet.getString("project_role");
        return new Participant(user_id, first_name, last_name, e_mail, project_id, project_role_id, project_role);
    }
}
