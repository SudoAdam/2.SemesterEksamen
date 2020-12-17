/**
 * Integration test of jdbc connectivity
 *
 * @author Patrick Vincent Højstrøm
 * @version 1.0
 * @since 27-11-2020
 */
package com.example.demo.Data;

import org.junit.jupiter.api.Test;

import java.sql.Connection;

import static org.junit.jupiter.api.Assertions.*;

class ConnectorTest {
    private final Connector connector;

    ConnectorTest() {
        this.connector = new Connector();
    }

    @Test
    void getConnection_success() {
        Connection connection = connector.getConnection();
        assertNotNull(connection);
    }
}