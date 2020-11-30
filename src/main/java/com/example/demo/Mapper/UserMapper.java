/**
 * This class works to create specific domain objects.
 * See 'AbstractMapper' class for more information.
 *
 * @author Patrick Vincent Højstrøm
 * @version 1.0
 * @since 27-11-2020
 */
package com.example.demo.Mapper;

import com.example.demo.Domain.User;

import java.sql.ResultSet;

public class UserMapper extends AbstractMapper {
    @Override
    public User mapping(ResultSet resultSet) {
        return new User();
    }
}
