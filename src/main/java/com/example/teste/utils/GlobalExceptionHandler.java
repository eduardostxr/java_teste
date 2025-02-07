package com.example.teste.utils;

import com.example.teste.model.Retorno;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.dao.DataAccessException;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@ControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger logger = Logger.getLogger(GlobalExceptionHandler.class.getName());

    @ExceptionHandler(DataAccessException.class)
    public ResponseEntity<Retorno<?>> handleDatabaseError(DataAccessException ex) {
        logger.log(Level.SEVERE, "Erro no banco de dados", ex);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new Retorno<>("Erro ao acessar o banco de dados.", HttpStatus.INTERNAL_SERVER_ERROR, "Erro na consulta SQL."));
    }

    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<Retorno<?>> handleResponseStatusException(ResponseStatusException ex) {
        HttpStatus status = (HttpStatus) ex.getStatusCode();
        return ResponseEntity.status(status)
                .body(new Retorno<>(ex.getReason(), status, "Erro de autenticação ou permissão."));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Retorno<?>> handleGenericError(Exception ex) {
        logger.log(Level.SEVERE, "Erro inesperado", ex);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new Retorno<>("Erro interno no servidor.", HttpStatus.INTERNAL_SERVER_ERROR, "Falha inesperada no sistema."));
    }
}
