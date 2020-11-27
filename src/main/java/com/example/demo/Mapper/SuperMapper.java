package com.example.demo.Mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class SuperMapper {
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
        Object object = mapper(resultSet);
        return object;
    }

    public Object mapper(ResultSet resultSet) {
        return new Object();
    }
}
