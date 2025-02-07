package com.example.teste.utils;

import com.example.teste.model.Retorno;
import org.springframework.http.HttpStatus;

import java.util.logging.Level;
import java.util.logging.Logger;

public class ServiceResponseHandler {
    private static final Logger logger = Logger.getLogger(ServiceResponseHandler.class.getName());

    // 201 Created
    public static <T> Retorno<T> created(T value, String message) {
        return new Retorno<>(value, message, HttpStatus.CREATED);
    }

    // 400 Bad Request
    public static <T> Retorno<T> badRequest(String userMessage, String error) {
        return new Retorno<>(userMessage, HttpStatus.BAD_REQUEST, error);
    }

    // 401 Unauthorized
    public static <T> Retorno<T> unauthorized(String userMessage) {
        return new Retorno<>(null, userMessage, HttpStatus.UNAUTHORIZED);
    }

    // 403 Forbidden
    public static <T> Retorno<T> forbidden(String userMessage) {
        return new Retorno<>(null, userMessage, HttpStatus.FORBIDDEN);
    }

    // 404 Not Found
    public static <T> Retorno<T> notFound(String userMessage) {
        return new Retorno<>(null, userMessage, HttpStatus.NOT_FOUND);
    }

    // 409 Conflict
    public static <T> Retorno<T> conflict(String userMessage) {
        return new Retorno<>(null, userMessage, HttpStatus.CONFLICT);
    }

    // 500 Internal Server Error
    public static <T> Retorno<T> internalError(String userMessage, String internalError, Exception e) {
        logger.log(Level.SEVERE, internalError, e);
        return new Retorno<>(null, userMessage, internalError, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
