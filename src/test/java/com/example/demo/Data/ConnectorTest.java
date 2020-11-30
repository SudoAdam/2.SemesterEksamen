package com.example.demo.Data;

import org.junit.jupiter.api.Test;

import java.sql.Connection;

import static org.junit.jupiter.api.Assertions.*;

class ConnectorTest {

    @Test
    void getConnection() {
        Connector connector = new Connector();
        Connection connection = connector.getConnection();
        assertNotNull(connection);
    }
}