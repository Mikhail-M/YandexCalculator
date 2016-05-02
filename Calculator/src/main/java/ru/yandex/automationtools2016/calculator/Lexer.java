package ru.yandex.automationtools2016.calculator;


import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


class Lexer {

    private String deleteSpaces(final String expression) {
        StringBuilder stringBuilder = new StringBuilder("");
        for (int i = 0; i < expression.length(); i++) {
            if (expression.charAt(i) != ' ') {
                stringBuilder.append(expression.charAt(i));
            }
        }
        return stringBuilder.toString();
    }

    private Boolean tryAddTokenOperator(final String expression) {
        TokenState temp = tryOperator(expression, currentInputPosition);
        if (temp.isParsed()) {
            tokenBuff.add(temp.getToken());
            return true;
        }
        return false;
    }

    private Boolean tryAddTokenBracket(final String expression) {
        TokenState temp = tryBracket(expression, currentInputPosition);
        if (temp.isParsed()) {
            tokenBuff.add(temp.getToken());
            return true;
        }
        return false;
    }

    private Boolean tryAddTokenDouble(final String expression) {
        TokenState temp = tryDouble(expression, currentInputPosition);
        if (temp.isParsed()) {
            tokenBuff.add(temp.getToken());
            return true;
        }
        return false;
    }

    private Boolean tryAddTokenFunction(final String expression) {
        TokenState temp = tryFunction(expression, currentInputPosition);
        if (temp.isParsed()) {
            tokenBuff.add(temp.getToken());
            return true;
        }
        return false;
    }


    Lexer(final String expression) throws SintaxErrorException {
        tokenBuff = new ArrayList<>();
        currentPosition = 0;
        String currExpression = deleteSpaces(expression);
        for (currentInputPosition = 0; currentInputPosition < currExpression.length(); currentInputPosition++) {
            TokenState temp;
            if (tryAddTokenOperator(currExpression)) {
                continue;
            }
            if (tryAddTokenBracket(currExpression)) {
                continue;
            }
            if (tryAddTokenDouble(currExpression)) {
                continue;
            }
            if (tryAddTokenFunction(currExpression)) {
                continue;
            } else {
                throw new SintaxErrorException("Sintax Error: unrecognized symbol");
            }
        }

        tokenBuff.add(new Token(Token.TokenType.NONE, -1, "~", Constants.END_PRIORITY));
    }

    public void next() {
        currentPosition++;
    }

    public Token currentToken() {
        return tokenBuff.get(currentPosition);
    }

    private List<Token> tokenBuff;
    private int currentPosition;
    private int currentInputPosition;

    private TokenState tryFunction(final String expr, final int pos) {
        if (!Character.isLetter(expr.charAt(pos))) {
            return new TokenState(false, null);
        }

        StringBuilder stringBuilder = new StringBuilder("");
        int posTemp = pos;
        while (expr.charAt(posTemp) != '(') {
            stringBuilder.append(expr.charAt(posTemp));
            posTemp++;

        }
        currentInputPosition = posTemp - 1;

        return new TokenState(true, new Token(Token.TokenType.FUNCTION, pos, stringBuilder.toString()));
    }

    private TokenState tryDouble(final String expr, final int pos) {

        Pattern p = Pattern.compile("[0-9]*\\.?[0-9]+([eE][-+]?[0-9]+)?");
        Matcher m = p.matcher(expr);
        if (m.find(pos) && m.start() == pos) {
            String ans = m.group();
            currentInputPosition = pos + ans.length() - 1;
            return new TokenState(true, new Token(Token.TokenType.DOUBLE, pos, ans));
        } else {
            return new TokenState(false, null);
        }
    }

    private TokenState tryBracket(final String expr, final int pos) {

        if (expr.charAt(pos) == '(') {
            return new TokenState(true, new Token(Token.TokenType.LEFTBRACKET, pos, "("));
        } else if (expr.charAt(pos) == ')') {
            return new TokenState(true, new Token(Token.TokenType.RIGHTBRACKET, pos, ")"));
        } else {
            return new TokenState(false, null);
        }
    }


    enum OperatorTokenBuilder {
        ADD('+') {
            public TokenState getToken(final int pos) {
                return new TokenState(true, new Token(Token.TokenType.ADD, pos, String.valueOf(getOperatorName()),
                        Constants.FIRST_PRIORITY));
            }
        },
        MINUS('-') {
            public TokenState getToken(final int pos) {
                return new TokenState(true, new Token(Token.TokenType.SUB, pos, String.valueOf(getOperatorName()),
                        Constants.FIRST_PRIORITY));
            }
        },
        MULT('*') {
            public TokenState getToken(final int pos) {
                return new TokenState(true, new Token(Token.TokenType.MULT, pos, String.valueOf(getOperatorName()),
                        Constants.SECOND_PRIORITY));
            }
        },
        DIV('/') {
            public TokenState getToken(final int pos) {
                return new TokenState(true, new Token(Token.TokenType.DIV, pos, String.valueOf(getOperatorName()),
                        Constants.SECOND_PRIORITY));
            }
        },
        POWER('^') {
            public TokenState getToken(final int pos) {
                return new TokenState(true, new Token(Token.TokenType.SUB, pos, String.valueOf(getOperatorName()),
                        Constants.THIRD_PRIORITY));
            }
        };


        public char getOperatorName() {
            return operatorName;
        }

        public static OperatorTokenBuilder buildOperatorToken(final char code) {
            for (OperatorTokenBuilder operatorToken : OperatorTokenBuilder.values()) {
                if (code == operatorToken.operatorName) {
                    return operatorToken;
                }
            }
            return null;
        }

        private char operatorName;

        OperatorTokenBuilder(final char operatorName) {
            this.operatorName = operatorName;
        }

        public abstract TokenState getToken(final int pos);

    }
    private TokenState tryOperator(final String expr, final int pos) {

        OperatorTokenBuilder builder = OperatorTokenBuilder.buildOperatorToken(expr.charAt(pos));

        if (builder != null) {
            return builder.getToken(pos);
        } else {
            return new TokenState(false, null);
        }
    }
}



