package com.example.teste.utils;

import com.example.teste.model.Retorno;
import org.springframework.http.HttpStatus;

import java.util.List;

public class ServiceResponseHandler {

    public static <T> Retorno<T> created(T value, String message) {
        return new Retorno<>(value, message, HttpStatus.CREATED);
    }

    public static <T> Retorno<T> badRequest(String userMessage, String errors) {
        return new Retorno<>(userMessage, HttpStatus.BAD_REQUEST, errors);
    }
}
