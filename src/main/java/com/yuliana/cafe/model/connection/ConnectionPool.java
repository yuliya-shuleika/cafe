package com.yuliana.cafe.model.connection;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * Enum represents the pool of the database connections.
 * It keeps the queue of the free connections that can be given to users.
 * Enum use the {@code ProxyConnection} objects that represent database connections.
 * This is the realization of the singleton pattern. Usage of the enum makes it thread safe.
 *
 * @author Yulia Shuleiko
 */
public enum ConnectionPool {
    INSTANCE;

    private static final Logger logger = LogManager.getLogger();
    private BlockingQueue<ProxyConnection> freeConnections;
    private Queue<ProxyConnection> givenAwayConnections;
    private static final int POOL_CAPACITY = 10;

    ConnectionPool() {
        freeConnections = new LinkedBlockingDeque<>(POOL_CAPACITY);
        givenAwayConnections = new ArrayDeque<>();
        for (int i = 0; i < POOL_CAPACITY; i++) {
            Connection connection = ConnectionCreator.createConnection();
            ProxyConnection proxyConnection = new ProxyConnection(connection);
            freeConnections.add(proxyConnection);
        }
    }

    /**
     * Take the connection from the queue of the free connections
     * and place it to the queue of given connections.
     *
     * @return the {@code ProxyConnection} object
     */
    public ProxyConnection getConnection() {
        ProxyConnection connection = null;
        try {
            connection = freeConnections.take();
            givenAwayConnections.offer(connection);
        } catch (InterruptedException e) {
            logger.log(Level.WARN, "No free connections.");
        }
        return connection;
    }

    /**
     * Release the connection and return it back to the queue of free connections.
     *
     * @param connection the {@code Connection} object
     */
    public void releaseConnection(Connection connection) {
        if (connection.getClass() == ProxyConnection.class) {
            ProxyConnection proxyConnection = (ProxyConnection) connection;
            givenAwayConnections.remove(proxyConnection);
            freeConnections.offer(proxyConnection);
        }
    }

    /**
     * Destroy the connection pool. Close all connections in the queue.
     */
    public void destroyPool() {
        for (int i = 0; i < POOL_CAPACITY; i++) {
            try {
                freeConnections.take().reallyClose();
            } catch (SQLException | InterruptedException e) {
                logger.log(Level.ERROR, "Error destroying connection pool.");
            }
        }
        deregisterDrivers();
    }

    /**
     * Deregister the drivers.
     */
    private void deregisterDrivers() {
        DriverManager.getDrivers().asIterator().forEachRemaining(driver -> {
            try {
                DriverManager.deregisterDriver(driver);
            } catch (SQLException throwables) {
                logger.log(Level.ERROR, "Can't deregister driver.");
            }
        });
    }
}
