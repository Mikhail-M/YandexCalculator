package ru.yandex.automationtools2016.calculator;

public class SimpleCalculator implements Calculator {

    public double calculate(final String expression) throws SintaxErrorException {
        Lexer a = new Lexer(expression);
        Parser parser = new Parser(a);
        return parser.calculate();
    }

}

