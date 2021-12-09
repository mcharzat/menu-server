package com.cicdlectures.menuserver.exception;

import com.cicdlectures.menuserver.exception.IdNotFoundException;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.http.HttpStatus;

@ControllerAdvice
public class IdNotFoundHandler{

    @ResponseBody
    @ExceptionHandler(IdNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String IdNotFoundAdvice(IdNotFoundException ex) {
        return ex.getMessage();
    }
}
