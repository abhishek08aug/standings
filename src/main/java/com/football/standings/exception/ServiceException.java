package com.football.standings.exception;

public class ServiceException extends RuntimeException {
    private static final long serialVersionUID = 1111222233334444555L;

    public ServiceException(final String message, final Throwable cause) {
        super(message, cause);
    }
}