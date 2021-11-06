package ru.vsb.calculator.model;

import lombok.*;

import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
public class Request {

    @NotBlank(message = "First Argument must not be null!")
    private String firstArg;

    @NotBlank(message = "Second Argument must not be null!")
    private String secondArg;
}
