package ru.vsb.calculator.service;

import org.junit.jupiter.api.Test;
import ru.vsb.calculator.cxf.org.tempuri.Add;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class CalculatorServiceImplTest {

    public static CalculatorServiceImpl calculatorService = new CalculatorServiceImpl();

    @Test
    void add() {
        int res = calculatorService.add(1, 2);
        assertEquals(3, res);
    }

    @Test
    void subtract() {
        int res = calculatorService.subtract(2, 1);
        assertEquals(1, res);
    }

    @Test
    void multiply() {
        int res = calculatorService.multiply(2, 2);
        assertEquals(4, res);
    }

    @Test
    void divide() {
        int res = calculatorService.divide(2, 4);
        assertEquals(0, res);
    }
}