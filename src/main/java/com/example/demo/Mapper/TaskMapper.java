/**
 * This class works to create specific domain objects.
 * See 'AbstractMapper' class for more information.
 *
 * @author Patrick Vincent Højstrøm
 * @version 1.0
 * @since 27-11-2020
 */

package com.example.demo.Mapper;

import com.example.demo.Domain.Task;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class TaskMapper extends AbstractMapper {
    UserMapper userMapper = new UserMapper();

    // BEHAVIOR ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    @Override
    public Task mapping(ResultSet resultSet) throws SQLException {
        int task_id = resultSet.getInt("task_id");
        int project_id = resultSet.getInt("project_id");
        String task_name = resultSet.getString("task_name");
        String task_description = resultSet.getString("task_description");
        LocalDate kickoff = LocalDate.parse(resultSet.getString("kickoff"));
        LocalDate deadline = LocalDate.parse(resultSet.getString("deadline"));
        int task_leader_id = resultSet.getInt("task_leader_id");
        String e_mail = resultSet.getString("e_mail");
        String first_name = resultSet.getString("first_name");
        String last_name = resultSet.getString("last_name");
        String name = first_name + ' ' + last_name;

        return new Task(task_id, project_id, task_name, task_description, task_leader_id, name, e_mail, kickoff, deadline);
    }
}
