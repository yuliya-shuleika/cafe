package com.yuliana.cafe.connection;

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

public enum  ConnectionPool {
    INSTANCE;

    private static final Logger logger = LogManager.getLogger();
    private BlockingQueue<ProxyConnection> freeConnections;
    private Queue<ProxyConnection> givenAwayConnections;
    private static final int POOL_CAPACITY = 10;

    ConnectionPool(){
        freeConnections = new LinkedBlockingDeque<>(POOL_CAPACITY);
        givenAwayConnections = new ArrayDeque<>();
        for(int i = 0; i < POOL_CAPACITY; i++){
            Connection connection = ConnectionCreator.createConnection();
            ProxyConnection proxyConnection = new ProxyConnection(connection);
            freeConnections.add(proxyConnection);
        }
    }

    public ProxyConnection getConnection(){
        ProxyConnection connection = null;
        try{
            connection = freeConnections.take();
            givenAwayConnections.offer(connection);
        } catch (InterruptedException e) {
            logger.log(Level.WARN, "No free connections.");
        }
        return connection;
    }

    public void releaseConnection(Connection connection){
        if (connection.getClass() == ProxyConnection.class) {
            ProxyConnection proxyConnection = (ProxyConnection) connection;
            givenAwayConnections.remove(proxyConnection);
            freeConnections.offer(proxyConnection);
        }
    }

    public void destroyPool(){
        for(int i = 0; i < POOL_CAPACITY; i++){
            try {
                freeConnections.take().reallyClose();
            } catch (SQLException throwables) {
                logger.log(Level.ERROR, "Can't close connection.");
            } catch (InterruptedException e) {
                logger.log(Level.ERROR, "");
            }
        }
        deregisterDrivers();
    }

    private void deregisterDrivers(){
        DriverManager.getDrivers().asIterator().forEachRemaining(driver -> {
            try {
                DriverManager.deregisterDriver(driver);
            } catch (SQLException throwables) {
                logger.log(Level.ERROR, "Can't deregister driver.");
            }
        });
    }

}
