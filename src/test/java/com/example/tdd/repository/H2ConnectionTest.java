package com.example.tdd.repository;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.boot.test.context.SpringBootTest;

import javax.sql.DataSource;
import java.sql.Connection;

@JdbcTest
public class H2ConnectionTest {

    @Autowired
    private DataSource dataSource;

    @Test
    public void testConnection() throws Exception{
        try(Connection con = dataSource.getConnection()){
            assertNotNull(con); // Assertions.assertNotNull(object)
            System.out.println("H2 DB 연결됨");
        }
    }

}
