package com.yuliana.cafe.exception;

/**
 * An exception that thrown when the {@code DaoException} was caught.
 *
 * @author Yulia Shuleiko
 */
public class ServiceException extends Exception {

    /**
     * Constructs a {@code ServiceException} object with a given message.
     *
     * @param message message with information about error
     */
    public ServiceException(String message) {
        super(message);
    }

    /**
     * Constructs a {@code ServiceException} object with a given cause.
     *
     * @param cause cause
     */
    public ServiceException(Throwable cause) {
        super(cause);
    }
}
