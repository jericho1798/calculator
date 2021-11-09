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
import org.tempuri.AddResponse;
import org.tempuri.DivideResponse;
import org.tempuri.MultiplyResponse;
import org.tempuri.SubtractResponse;
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
                            schema = @Schema(implementation = AddResponse.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid arguments", content = @Content)
    })
    @GetMapping(path = "/add", produces = "application/json")
    public ResponseEntity<?> add(@RequestParam String firstArg, @RequestParam String secondArg) {
        AddResponse response = new AddResponse();
        response.setAddResult(calculatorService.add(
                Integer.parseInt(firstArg),
                Integer.parseInt(secondArg)));
        return new ResponseEntity<>(
                response.getAddResult(),
                HttpStatus.OK);
    }

    @Operation(summary = "Calculates the difference of 2 arguments")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Response received",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = SubtractResponse.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid arguments", content = @Content)
    })
    @GetMapping(path = "/subtract", produces = "application/json")
    public ResponseEntity<?> subtract(@RequestParam String firstArg, @RequestParam String secondArg) throws NumberFormatException {
        SubtractResponse response = new SubtractResponse();
        response.setSubtractResult(calculatorService.subtract(
                Integer.parseInt(firstArg),
                Integer.parseInt(secondArg)));
        return new ResponseEntity<>(
                response.getSubtractResult(),
                HttpStatus.OK);
    }

    @Operation(summary = "Calculates the product of 2 arguments")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Response received",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = MultiplyResponse.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid arguments", content = @Content)
    })
    @GetMapping(path = "/multiply", produces = "application/json")
    public ResponseEntity<?> multiply(@RequestParam String firstArg, @RequestParam String secondArg) throws NumberFormatException {
        MultiplyResponse response = new MultiplyResponse();
        response.setMultiplyResult(calculatorService.multiply(
                Integer.parseInt(firstArg),
                Integer.parseInt(secondArg)));
        return new ResponseEntity<>(
                response.getMultiplyResult(),
                HttpStatus.OK);
    }

    @Operation(summary = "Calculates the quotient of 2 arguments")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Response received",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = DivideResponse.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid arguments", content = @Content)
    })
    @GetMapping(path = "/divide", produces = "application/json")
    public ResponseEntity<?> divide(@RequestParam String firstArg, @RequestParam String secondArg) throws NumberFormatException {
        DivideResponse response = new DivideResponse();
        response.setDivideResult(calculatorService.divide(
                Integer.parseInt(firstArg),
                Integer.parseInt(secondArg)));
        return secondArg.equals("0")
                ? ResponseEntity.badRequest().body("Error: Division by 0!")
                : new ResponseEntity<>(
                response.getDivideResult(),
                HttpStatus.OK);
    }


    @ExceptionHandler(NumberFormatException.class)
    public ResponseEntity<?> handleException(NumberFormatException e) {
        return ResponseEntity.badRequest().body(e.getMessage() + " argument must be a number!");
    }


}
