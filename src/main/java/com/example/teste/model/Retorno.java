package com.example.teste.model;

import org.springframework.http.HttpStatus;
import java.util.List;

public class Retorno<T> {
    private T value;
    private String message;
    private int status;
    private String error;

    public Retorno(T value, String message) {
        this.value = value;
        this.message = message;
    }

    // Construtor para sucesso
    public Retorno(T value, String message, HttpStatus status) {
        this.value = value;
        this.message = message;
        this.status = status.value();
    }

    // Construtor para erro
    public Retorno(String message, HttpStatus status, String error) {
        this.value = null;
        this.message = message;
        this.status = status.value();
        this.error = error;
    }

    // Construtor para erro com detalhes internos (como no caso de Exception)
    public Retorno(T value, String message, String error, HttpStatus status) {
        this.value = value;
        this.message = message;
        this.error = error;
        this.status = status.value();
    }

    // Getters e Setters
    public int getStatus() { return status; }
    public String getError() { return error; }
    public String getMessage() { return message; }
    public T getValue() { return value; }

    public void setMessage(String message) {
        this.message = message;
    }
}
