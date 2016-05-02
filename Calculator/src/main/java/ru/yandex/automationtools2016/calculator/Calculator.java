package ru.yandex.automationtools2016.calculator;

public interface Calculator {
    double calculate(String expression) throws SintaxErrorException;
}
