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
import com.example.demo.Domain.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class UserMapper extends AbstractMapper {
    // BEHAVIOR ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    @Override
    public User mapping(ResultSet resultSet) throws SQLException{
        int userId = resultSet.getInt("userId");
        String firstName = resultSet.getString("firstName");
        String lastName = resultSet.getString("lastName");
        String email = resultSet.getString("email");
        String projects = resultSet.getString("projects");
        int isAdmin = resultSet.getInt("isAdmin");
        String jobTitle = resultSet.getString("jobTitle");
        return new User(userId, firstName, lastName, email, projects, isAdmin, jobTitle);
    }
}
