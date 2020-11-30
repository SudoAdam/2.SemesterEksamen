/**
 * This class works to create specific domain objects.
 * See 'AbstractMapper' class for more information.
 *
 * @author Patrick Vincent Højstrøm
 * @version 1.0
 * @since 27-11-2020
 */
package com.example.demo.Mapper;

import com.example.demo.Domain.Project;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class ProjectMapper extends AbstractMapper {
    @Override
    public Project mapping(ResultSet resultSet) throws SQLException {
        int project_id = resultSet.getInt("project_id");
        String name = resultSet.getString("project_name");
        Date kickoff = resultSet.getDate("kickoff");
        Date deadline = resultSet.getDate("deadline");
        int project_leader_id = resultSet.getInt("project_leader_id");
        int customer_id = resultSet.getInt("customer_id");
        return new Project(project_id, name, kickoff, deadline, project_leader_id, customer_id);
    }
}
