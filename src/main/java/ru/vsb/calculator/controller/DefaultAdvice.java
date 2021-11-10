package ru.vsb.calculator.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@ControllerAdvice
public class DefaultAdvice {

    @ExceptionHandler(NumberFormatException.class)
    public ResponseEntity<String> handleNumberFormatException(NumberFormatException e, HttpServletResponse response) throws IOException {
        return new ResponseEntity<>(e.getMessage() + " argument must be a number!", HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ArithmeticException.class)
    public ResponseEntity<String> handleArithmeticalException(ArithmeticException e, HttpServletResponse response) throws IOException {
        return new ResponseEntity<>("Divide by 0!", HttpStatus.BAD_REQUEST);
    }
}
