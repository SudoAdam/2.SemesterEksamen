package com.example.demo.Data;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

import java.sql.Connection;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ConnectorTest {

    @Test
    void getConnection(ApplicationContext ctx) {
        Connector connector = (Connector) ctx.getBean("connector");
        Connection connection = connector.getConnection();
        assertNotNull(connection);
    }
}