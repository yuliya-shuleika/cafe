package com.yuliana.cafe.connection;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

class ConnectionCreator {

    private static final Logger logger = LogManager.getLogger();
    private static final String PROPERTIES_PATH = "pool_config/config.properties";
    private static final String DRIVER_PROPERTY = "db.driver";
    private static final String URL_PROPERTY = "db.url";
    private static final Properties PROPERTIES = new Properties();

    static {
        try {
            ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
            InputStream input = classLoader.getResourceAsStream(PROPERTIES_PATH);
            PROPERTIES.load(input);
            String driver = PROPERTIES.getProperty(DRIVER_PROPERTY);
            Class.forName(driver);
        } catch (IOException | ClassNotFoundException e) {
            logger.log(Level.FATAL, "Error during connecting driver", e);
        }
    }

    private ConnectionCreator() {
    }

    static Connection createConnection() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(PROPERTIES.getProperty(URL_PROPERTY), PROPERTIES);
        } catch (SQLException throwables) {
            logger.log(Level.FATAL, "Couldn't create connection.");
            throw new RuntimeException();
        }
        return connection;
    }
}
