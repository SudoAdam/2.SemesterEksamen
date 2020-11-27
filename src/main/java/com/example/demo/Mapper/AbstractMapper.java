/**
 * This class is the abstraction for all Mapper classes.
 *
 * In this class there are 2 methods for use by external access.
 *      1. create(): Will create a single object from resultSet and returns Object
 *      2. batch(): Will create multiple objects from resultSet and returns ArrayList
 *
 * @author Patrick Vincent Højstrøm
 * @version 1.0
 * @since 27-11-2020
 */
package com.example.demo.Mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public abstract class AbstractMapper {
    // Template pattern: Larman: Kap 37
    // Martin Fowler: Identity field: Patterns of enterprice and architecture
    // Loader fra map i cache
    public ArrayList<Object> batch(ResultSet resultSet) throws SQLException {
        ArrayList<Object> list = new ArrayList<>();
        while (resultSet.next()) {
            Object object = create(resultSet);
            list.add(object);
        }
        return list;
    }
    public Object create(ResultSet resultSet) throws SQLException {
        if (resultSet.isBeforeFirst()) {
            resultSet.next();
        }
        return mapping(resultSet);
    }
    public abstract Object mapping(ResultSet resultSet);
}
