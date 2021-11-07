package ru.vsb.calculator.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.tempuri.Calculator;
import org.tempuri.CalculatorSoap;

@Service
@Slf4j
public class CalculatorServiceImpl implements CalculatorService{

    private final Calculator calculator = new Calculator();
    private final CalculatorSoap port = calculator.getCalculatorSoap();

    @Cacheable("add")
    @Override
    public int add(int firstElement, int secondElement) {
        int response = port.add(firstElement, secondElement);
        log.info("DOING SOME ADD...");
        return response;
    }
    @Cacheable("subtract")
    @Override
    public int subtract(int firstElement, int secondElement) {
        int response = port.subtract(firstElement, secondElement);
        log.info("DOING SOME SUBTRACT...");
        return response;
    }

    @Cacheable("multiply")
    @Override
    public int multiply(int firstElement, int secondElement) {
        int response = port.multiply(firstElement, secondElement);
        log.info("DOING SOME MULTIPLY...");
        return response;
    }

    @Cacheable("divide")
    @Override
    public int divide(int firstElement, int secondElement) {
        int response = port.divide(firstElement, secondElement);
        log.info("DOING SOME DIVIDE...");
        return response;
    }
}
