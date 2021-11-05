package ru.vsb.calculator.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CalculatorServiceImplTest {

    public static CalculatorServiceImpl cS = new CalculatorServiceImpl();
    @Test
    void add() {
        int res = cS.add(1, 2);
        assertEquals(3, res);
    }

    @Test
    void subtract() {
        int res = cS.subtract(2, 1);
        assertEquals(1, res);
    }

    @Test
    void subtractM() {
        int res = cS.subtract(1, 2);
        assertEquals(-1, res);
    }

    @Test
    void multiply() {
        int res = cS.multiply(2, 2);
        assertEquals(4, res);
    }

    @Test
    void divide() {
        int res = cS.divide(2, 4);
        assertEquals(0, res);
    }
}