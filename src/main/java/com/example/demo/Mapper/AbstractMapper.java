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
import com.example.demo.Exceptions.MapperExceptions.EmptyResultSetException;

import java.sql.ResultSet;
import java.sql.SQLException;

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

    public DomainInterface create(ResultSet resultSet) throws EmptyResultSetException {
        try {
            if (resultSet.isBeforeFirst()) {
                resultSet.next();
            }
            return mapping(resultSet);
        } catch (SQLException e) {
            throw new EmptyResultSetException();
        }
    }

    public abstract DomainInterface mapping(ResultSet resultSet) throws SQLException;
}
