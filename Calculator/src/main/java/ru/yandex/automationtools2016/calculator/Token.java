package ru.yandex.automationtools2016.calculator;

public class Token {


    public Token(final TokenType tokenType, final int position, final String value) {
        this.tokenType = tokenType;
        this.position = position;
        this.value = value;
        this.priority = 0;
    }

    public Token(final TokenType tokenType, final int position, final String value, final int priority) {
        this.tokenType = tokenType;
        this.position = position;
        this.value = value;
        this.priority = priority;
    }

    public enum TokenType {
        ADD,
        SUB,
        MULT,
        DIV,
        POWER,
        LEFTBRACKET,
        RIGHTBRACKET,
        FUNCTION,
        DOUBLE,
        NONE;
    }


    public TokenType getTokenType() {
        return tokenType;
    }

    private TokenType tokenType;

    public int getPosition() {
        return position;
    }

    private int position;

    public String getValue() {
        return value;
    }

    private int priority;

    public int getPriority() {
        return priority;
    }

    private String value;
}
