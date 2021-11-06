package ru.vsb.calculator.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class CalculatorServiceImpl implements CalculatorService{
    @Cacheable("add")
    @Override
    public int add(int firstElement, int secondElement) {
        log.info("DOING SOME ADD...");
        return firstElement + secondElement;
    }

    @Cacheable("subtract")
    @Override
    public int subtract(int firstElement, int secondElement) {
        log.info("DOING SOME SUBTRACT...");
        return firstElement - secondElement;
    }

    @Cacheable("multiply")
    @Override
    public int multiply(int firstElement, int secondElement) {
        log.info("DOING SOME MULTIPLY...");
        return firstElement * secondElement;
    }

    @Cacheable("divide")
    @Override
    public int divide(int firstElement, int secondElement) {
        log.info("DOING SOME DIVIDE...");
        return firstElement / secondElement;
    }
}
