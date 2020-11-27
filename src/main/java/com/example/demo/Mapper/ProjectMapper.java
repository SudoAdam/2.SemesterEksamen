/**
 * This class works to create new objects of a special type.
 * See 'AbstractMapper' class for more specifics.
 *
 * @author Patrick Vincent Højstrøm
 * @version 1.0
 * @since 27-11-2020
 */
package com.example.demo.Mapper;

import com.example.demo.Domain.Project;

import java.sql.ResultSet;

public class ProjectMapper extends AbstractMapper {
    @Override
    public Project mapping(ResultSet resultSet) {
        return new Project();
    }
}
