package com.raderleao.admin.catalogo.infrastructure.api.controllers;

import com.raderleao.admin.catalogo.domain.exceptions.DomainException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import com.raderleao.admin.catalogo.domain.validation.Error;

import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<?> handleDomainExceprion(final DomainException ex) {
       return ResponseEntity.unprocessableEntity().body()
    }

    record ApiError(String message, List<Error> erros) {

    }
}
