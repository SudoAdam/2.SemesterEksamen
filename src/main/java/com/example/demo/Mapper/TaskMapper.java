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
import com.example.demo.Domain.User;

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

        User u = userMapper.mapping(resultSet);

        return new Task(task_id, project_id, task_name, task_description,u,kickoff,deadline);
    }
}
