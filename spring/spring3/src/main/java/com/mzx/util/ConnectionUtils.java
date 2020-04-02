package com.mzx.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
@Component
public class ConnectionUtils {

    private static ThreadLocal<Connection> currentThreadLocal = new ThreadLocal<>();
    @Autowired
    private DataSource dataSource;

    @Bean
    public Connection getConnection() throws SQLException {
        Connection connection = currentThreadLocal.get();
        if (connection == null) {
            connection = dataSource.getConnection();
            currentThreadLocal.set(connection);
        }
        return connection;
    }

    public void removeTreadLocal() {
        currentThreadLocal.remove();
    }

}
