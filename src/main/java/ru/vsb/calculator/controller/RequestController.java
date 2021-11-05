package ru.vsb.calculator.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.vsb.calculator.model.Request;
import ru.vsb.calculator.model.Response;
import ru.vsb.calculator.service.CalculatorService;

import javax.validation.Valid;

@RestController
@RequestMapping("/calculator")
public class RequestController {

    private final CalculatorService calculatorService;

    @Autowired
    public RequestController(CalculatorService calculatorService) {
        this.calculatorService = calculatorService;
    }

    @PostMapping(path = "/add", produces = "application/json")
    public Response add(@RequestBody Request request) {
        return new Response(calculatorService.add(
                request.getFirstArg(),
                request.getSecondArg()));
    }

    @PostMapping(path = "/subtract", produces = "application/json")
    public Response subtract(@RequestBody Request request) {
        return new Response(calculatorService.subtract(
                request.getFirstArg(),
                request.getSecondArg()));
    }

    @PostMapping(path = "/multiply", produces = "application/json")
    public Response multiply(@RequestBody Request request) {
        return new Response(calculatorService.multiply(
                request.getFirstArg(),
                request.getSecondArg()));
    }

    @PostMapping(path = "/divide", produces = "application/json")
    public Response divide(@RequestBody Request request) {
        int secondArg = request.getSecondArg();
        return secondArg == 0 ? new Response(0) :new Response(calculatorService.divide(
                request.getFirstArg(),
                request.getSecondArg()));
    }


}
