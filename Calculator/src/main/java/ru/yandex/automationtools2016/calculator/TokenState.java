package ru.yandex.automationtools2016.calculator;


public class TokenState {

    public TokenState(final boolean state, final Token token) {
        this.state = state;
        this.token = token;
    }

    private boolean state;

    public boolean isParsed() {
        return state;
    }

    public Token getToken() {
        return token;
    }

    private Token token;
}
