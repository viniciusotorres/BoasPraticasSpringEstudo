package com.example.estudo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

/**
 * Classe para tratar exceções globais.
 * Trata exceções do tipo IllegalArgumentException e Exception.
 * Retorna um ResponseEntity com a mensagem de erro e status HTTP.
 * @ControllerAdvice - Anotação para tratar exceções globais.
 * @ExceptionHandler - Anotação para tratar exceções específicas.
 * @param ex Exceção a ser tratada.
 *           * @param request Requisição web.
 *           * @return ResponseEntity com a mensagem de erro e status HTTP.
 *
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Trata exceções do tipo IllegalArgumentException.
     * @param ex Exceção a ser tratada.
     * @param request Requisição web.
     * @return ResponseEntity com a mensagem de erro e status HTTP.
     */
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Object> handleIllegalArgumentException(IllegalArgumentException ex, WebRequest request) {
        Map<String, Object> body = new HashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("status", HttpStatus.BAD_REQUEST.value());
        body.put("error", "Bad Request");
        body.put("message", ex.getMessage());
        body.put("path", request.getDescription(false).substring(4));

        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }

    /**
     * Trata exceções do tipo Exception.
     * @param ex Exceção a ser tratada.
     * @param request Requisição web.
     * @return ResponseEntity com a mensagem de erro e status HTTP.
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleGenericException(Exception ex, WebRequest request) {
        Map<String, Object> body = new HashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("status", HttpStatus.INTERNAL_SERVER_ERROR.value());
        body.put("error", "Internal Server Error");
        body.put("message", ex.getMessage());
        body.put("path", request.getDescription(false).substring(4));

        return new ResponseEntity<>(body, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
