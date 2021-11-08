package ru.vsb.calculator.controller;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.vsb.calculator.model.Request;
import ru.vsb.calculator.model.Response;
import ru.vsb.calculator.service.CalculatorService;


@Slf4j
@RestController
@OpenAPIDefinition(info = @Info(title = "Calculator", description = "REST service-adapter to SOAP web service"))
@RequestMapping("/calculator")
public class RequestController {

    private final CalculatorService calculatorService;

    @Autowired
    public RequestController(CalculatorService calculatorService) {
        this.calculatorService = calculatorService;
    }

    @Operation(summary = "Calculate the sum of 2 arguments")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Response received",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Response.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid arguments", content = @Content)
    })
    @GetMapping(path = "/add", produces = "application/json")
    public ResponseEntity<?> add(@RequestParam String firstArg, @RequestParam String secondArg) {
        Request request = new Request(firstArg, secondArg);
        return new ResponseEntity<>(
                new Response(calculatorService.add(
                        Integer.parseInt(request.getFirstArg()),
                        Integer.parseInt(request.getSecondArg()))
                ),
                HttpStatus.OK);
    }

    @Operation(summary = "Calculates the difference of 2 arguments")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Response received",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Response.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid arguments", content = @Content)
    })
    @GetMapping(path = "/subtract", produces = "application/json")
    public ResponseEntity<?> subtract(@RequestParam String firstArg, @RequestParam String secondArg) throws NumberFormatException {
        Request request = new Request(firstArg, secondArg);
        return new ResponseEntity<>(
                new Response(calculatorService.subtract(
                        Integer.parseInt(request.getFirstArg()),
                        Integer.parseInt(request.getSecondArg()))
                ),
                HttpStatus.OK);
    }

    @Operation(summary = "Calculates the product of 2 arguments")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Response received",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Response.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid arguments", content = @Content)
    })
    @GetMapping(path = "/multiply", produces = "application/json")
    public ResponseEntity<?> multiply(@RequestParam String firstArg, @RequestParam String secondArg) throws NumberFormatException {
        Request request = new Request(firstArg, secondArg);
        return new ResponseEntity<>(
                new Response(calculatorService.multiply(
                        Integer.parseInt(request.getFirstArg()),
                        Integer.parseInt(request.getSecondArg()))
                ),
                HttpStatus.OK);
    }

    @Operation(summary = "Calculates the quotient of 2 arguments")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Response received",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Response.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid arguments", content = @Content)
    })
    @GetMapping(path = "/divide", produces = "application/json")
    public ResponseEntity<?> divide(@RequestParam String firstArg, @RequestParam String secondArg) throws NumberFormatException {
        Request request = new Request(firstArg, secondArg);
        return request.getSecondArg().equals("0")
                ? ResponseEntity.badRequest().body("Error: Division by 0!")
                : new ResponseEntity<>(
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
