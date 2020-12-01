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

import java.sql.ResultSet;
import java.sql.SQLException;

public abstract class AbstractMapper {
    // Kommentar fra Tine
    // Læs om: Template pattern: Larman: Kap 37
    // Se efter: Martin Fowler: Identity field: Patterns of enterprice and architecture
    // Se efter: genbrug objekter fra cache

    /*public ArrayList<DomainFacade> batch(ResultSet resultSet) throws SQLException {
        ArrayList<DomainFacade> list = new ArrayList<>();
        while (resultSet.next()) {
            list.add(create(resultSet));
        }
        return list;
    }*/

    public DomainFacade create(ResultSet resultSet) throws SQLException {
        if (resultSet.isBeforeFirst()) {
            resultSet.next();
        }
        return mapping(resultSet);
    }

    public abstract DomainFacade mapping(ResultSet resultSet) throws SQLException;
}
