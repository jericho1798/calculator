package ru.vsb.calculator.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Request {

    private int firstArg;

    private int secondArg;
}
