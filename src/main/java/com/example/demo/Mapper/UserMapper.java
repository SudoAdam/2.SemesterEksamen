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
        int user_id = resultSet.getInt("user_id");
        String e_mail = resultSet.getString("e_mail");
        String password = resultSet.getString("password");
        String first_name = resultSet.getString("first_name");
        String last_name = resultSet.getString("last_name");
        int is_admin = resultSet.getInt("is_admin");
        int project_role = resultSet.getInt("project_role");
        return new User(user_id, e_mail, password, first_name, last_name, is_admin, project_role);
    }
}
