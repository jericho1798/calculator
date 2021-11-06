package ru.vsb.calculator.controller;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import ru.vsb.calculator.model.Response;
import ru.vsb.calculator.service.CalculatorService;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class CacheTest {
    @Autowired
    CalculatorService calculatorService;
    @Test
    public void add() {
        int out = calculatorService.add(1, 2);
        log.info(String.valueOf(out));
        out = calculatorService.add(1, 2);
        log.info(String.valueOf(out));
    }

    @Test
    public void subtract() {
        int out = calculatorService.subtract(1, 2);
        log.info(String.valueOf(out));
        out = calculatorService.subtract(1, 2);
        log.info(String.valueOf(out));
    }

    @Test
    public void multiply() {
        int out = calculatorService.multiply(3, 2);
        log.info(String.valueOf(out));
        out = calculatorService.multiply(3, 2);
        log.info(String.valueOf(out));
    }

    @Test
    public void divide() {
        int out = calculatorService.divide(4, 2);
        log.info(String.valueOf(out));
        out = calculatorService.divide(4, 2);
        log.info(String.valueOf(out));
    }
}
