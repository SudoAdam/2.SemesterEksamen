/**
 * @author Adam Madsen
 * @version 1.0
 * @since 09-12-2020
 */

package com.example.demo.Mapper;

import com.example.demo.Domain.SubTask;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SubTaskMapper extends AbstractMapper{
    @Override
    public SubTask mapping(ResultSet resultSet) throws SQLException {int sub_task_id = resultSet.getInt("sub_task_id");
        int task_id = resultSet.getInt("task_id");
        String sub_task_name = resultSet.getString("sub_task_name");
        String sub_task_description = resultSet.getString("sub_task_description");
        return new SubTask(sub_task_id, sub_task_name, sub_task_description, task_id);
    }
}
