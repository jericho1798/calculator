package ru.vsb.calculator.service;

import org.springframework.stereotype.Service;

@Service
public class CalculatorServiceImpl implements CalculatorService{
    @Override
    public int add(int firstElement, int secondElement) {
        return firstElement + secondElement;
    }

    @Override
    public int subtract(int firstElement, int secondElement) {
        return firstElement - secondElement;
    }

    @Override
    public int multiply(int firstElement, int secondElement) {
        return firstElement * secondElement;
    }

    @Override
    public int divide(int firstElement, int secondElement) {
        return firstElement / secondElement;
    }
}
