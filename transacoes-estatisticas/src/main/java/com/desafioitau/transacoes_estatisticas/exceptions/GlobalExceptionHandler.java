package com.desafioitau.transacoes_estatisticas.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import lombok.AllArgsConstructor;
import lombok.Getter;

@RestControllerAdvice

public class GlobalExceptionHandler {
    
    @ExceptionHandler(UnprocessableEntity.class)
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    public ErrorResponse handleUnprocessableEntity(UnprocessableEntity ex){
        return new ErrorResponse(ex.getMessage());
    }

    @Getter
    @AllArgsConstructor
    static class ErrorResponse {
        private String message;
    }

}
