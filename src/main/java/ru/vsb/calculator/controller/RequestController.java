package ru.vsb.calculator.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.vsb.calculator.model.Request;
import ru.vsb.calculator.model.Response;
import ru.vsb.calculator.service.CalculatorService;
import ru.vsb.calculator.validation.ValidationErrorBuilder;

import javax.validation.Valid;

@Slf4j
@RestController
@RequestMapping("/calculator")
public class RequestController {

    private final CalculatorService calculatorService;

    @Autowired
    public RequestController(CalculatorService calculatorService) {
        this.calculatorService = calculatorService;
    }



    @GetMapping(path = "/add", produces = "application/json")
    public ResponseEntity<?> add(@Valid @RequestBody Request request, BindingResult bindingResult) throws NumberFormatException {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body(ValidationErrorBuilder.fromBindingErrors(bindingResult));
        }

        return new ResponseEntity<>(
                new Response(calculatorService.add(
                        Integer.parseInt(request.getFirstArg()),
                        Integer.parseInt(request.getSecondArg()))
                ),
                HttpStatus.OK);
    }

    @GetMapping(path = "/subtract", produces = "application/json")
    public ResponseEntity<?> subtract(@Valid @RequestBody Request request, BindingResult bindingResult) throws NumberFormatException {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body(ValidationErrorBuilder.fromBindingErrors(bindingResult));
        }

        return new ResponseEntity<>(
                new Response(calculatorService.subtract(
                        Integer.parseInt(request.getFirstArg()),
                        Integer.parseInt(request.getSecondArg()))
                ),
                HttpStatus.OK);
    }

    @GetMapping(path = "/multiply", produces = "application/json")
    public ResponseEntity<?> multiply(@Valid @RequestBody Request request, BindingResult bindingResult) throws NumberFormatException {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body(ValidationErrorBuilder.fromBindingErrors(bindingResult));
        }

        return new ResponseEntity<>(
                new Response(calculatorService.multiply(
                        Integer.parseInt(request.getFirstArg()),
                        Integer.parseInt(request.getSecondArg()))
                ),
                HttpStatus.OK);
    }

    @GetMapping(path = "/divide", produces = "application/json")
    public ResponseEntity<?> divide(@Valid @RequestBody Request request, BindingResult bindingResult) throws NumberFormatException {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body(ValidationErrorBuilder.fromBindingErrors(bindingResult));
        }

        return new ResponseEntity<>(
                new Response(calculatorService.divide(
                        Integer.parseInt(request.getFirstArg()),
                        Integer.parseInt(request.getSecondArg()))
                ),
                HttpStatus.OK);
    }


    @ExceptionHandler(NumberFormatException.class)
    public ResponseEntity<?> handleException(NumberFormatException e) {
        return ResponseEntity.badRequest().body(e.getMessage() + " argument must be a number!");
    }


}
