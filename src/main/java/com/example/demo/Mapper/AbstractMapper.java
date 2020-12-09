/**
 * This class is the abstraction for all Mapper classes.
 *
 * A single abstract method is required to be overridden by derived classes. This is
 * the mapping() method that simply describes how information from the ResultSet is
 * transferred to the designated business object.
 *
 * Reference: Larman: Template Method Pattern
 *
 * @author Patrick Vincent Højstrøm
 * @version 1.0
 * @since 27-11-2020
 */
package com.example.demo.Mapper;

import com.example.demo.Domain.DomainFacade;
import com.example.demo.Exceptions.QueryDeniedException;

import java.sql.ResultSet;
import java.sql.SQLException;

public abstract class AbstractMapper {
    /*public ArrayList<DomainFacade> batch(ResultSet resultSet) throws SQLException {
        ArrayList<DomainFacade> list = new ArrayList<>();
        while (resultSet.next()) {
            list.add(create(resultSet));
        }
        return list;
    }*/

    public DomainFacade create(ResultSet resultSet) throws QueryDeniedException {
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

    public abstract DomainFacade mapping(ResultSet resultSet) throws SQLException;
}
