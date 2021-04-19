package com.yuliana.cafe.controller.listener;

import com.yuliana.cafe.connection.ConnectionPool;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class ContextListener implements ServletContextListener {

    private static final Logger logger = LogManager.getLogger();
    private static final ConnectionPool pool = ConnectionPool.INSTANCE;

    public ContextListener() {
    }

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        logger.log(Level.DEBUG, "Context was created.");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        pool.destroyPool();
        logger.log(Level.DEBUG, "Context was destroyed.");
    }
}
