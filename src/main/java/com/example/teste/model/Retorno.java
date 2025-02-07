package com.example.teste.model;

import org.springframework.http.HttpStatus;
import java.util.List;

public class Retorno<T> {
    private T value;
    private String message;
    private int status;
    private String internalServerError;

    public Retorno(T value, String message, HttpStatus status) {
        this.value = value;
        this.message = message;
        this.status = status.value();
    }

    public Retorno(String message, HttpStatus status, String errors) {
        this.value = null;
        this.message = message;
        this.status = status.value();
        this.internalServerError = errors;
    }

    public int getStatus() { return status; }
}
