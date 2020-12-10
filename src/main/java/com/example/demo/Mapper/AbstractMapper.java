/**
 * This class is the abstraction for all Mapper classes.
 *
 * A single abstract method is required to be overridden by derived classes. This is
 * the mapping() method that simply describes how information from the ResultSet is
 * transferred to the designated business object.
 *
 * Reference: Larman: Template Method Pattern
 *
 * A future implementation could be the use of Generics to avoid type casting outside the Mapper class
 *
 * @author Patrick Vincent Højstrøm
 * @version 1.0
 * @since 27-11-2020
 */
package com.example.demo.Mapper;

import com.example.demo.Domain.DomainInterface;
import com.example.demo.Exceptions.QueryDeniedException;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public abstract class AbstractMapper {
    /*
    public ArrayList<?> batch(ResultSet resultSet) throws SQLException, QueryDeniedException {
        ArrayList<?> list = new ArrayList<>();
        while (resultSet.next()) {
            list.add(create(resultSet));
        }
        return list;
    }
    */

    public DomainInterface create(ResultSet resultSet) throws QueryDeniedException {
        try {
            if (resultSet.isBeforeFirst()) {
                if (!resultSet.next()) {
                    throw new QueryDeniedException("Error scrubbing resultSet: Message: Empty resultSet");
                }
            }
            return mapping(resultSet);
        } catch (SQLException e) {
            throw new QueryDeniedException("Error reading resultSet: SQLException message: " + e.getMessage());
        }
    }

    public abstract DomainInterface mapping(ResultSet resultSet) throws SQLException;
}
