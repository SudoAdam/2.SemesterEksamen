/**
 * This class is the abstraction for all Mapper classes.
 *
 * In this class there are 2 methods for use by external access.
 * 1. create(): Will create a single object from resultSet and returns Object
 * 2. batch(): Will create multiple objects from resultSet and returns ArrayList
 *
 * A single abstract method is required to be overridden by derived classes. This is
 * the mapping() method that simply describes how information from the ResultSet is
 * transferred to the designated business object.
 *
 * @author Patrick Vincent Højstrøm
 * @version 1.0
 * @since 27-11-2020
 */
package com.example.demo.Mapper;

import com.example.demo.Domain.DomainFacade;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public abstract class AbstractMapper {
    // Kommentar fra Tine
    // Læs om: Template pattern: Larman: Kap 37
    // Se efter: Martin Fowler: Identity field: Patterns of enterprice and architecture
    // Se efter: genbrug objekter fra cache
    public ArrayList<DomainFacade> batch(ResultSet resultSet) throws SQLException {
        ArrayList<DomainFacade> list = new ArrayList<>();
        while (resultSet.next()) {
            list.add(create(resultSet));
        }
        return list;
    }
    public DomainFacade create(ResultSet resultSet) throws SQLException {
        if (resultSet.isBeforeFirst()) {
            resultSet.next();
        }
        return mapping(resultSet);
    }
    public abstract DomainFacade mapping(ResultSet resultSet) throws SQLException;
}
