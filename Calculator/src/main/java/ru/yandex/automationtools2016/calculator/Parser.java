package ru.yandex.automationtools2016.calculator;

public class Parser {

    private Lexer lex;
    public Parser(final Lexer lex) {
        this.lex = lex;
    }
    double calculate() throws SintaxErrorException {
        return calculateExpr(lex);
    }

    enum AtomCalculator {
        number(Token.TokenType.DOUBLE) {
            public double apply(final Lexer lex) {
                double x =  Double.parseDouble(lex.currentToken().getValue());
                lex.next();
                return x;
            }
        },
        unaryMinus(Token.TokenType.SUB) {
            public double apply(final Lexer lex) throws SintaxErrorException {
                lex.next();
                return -calculateTerm(lex);
            }
        },
        unaryPlus(Token.TokenType.ADD) {
            public double apply(final Lexer lex) throws SintaxErrorException {
                lex.next();
                return calculateTerm(lex);
            }
        },
        leftBracket(Token.TokenType.LEFTBRACKET) {
            public double apply(final Lexer lex) throws SintaxErrorException {
                lex.next();
                double e = calculateExpr(lex);
                if (lex.currentToken().getTokenType() != Token.TokenType.RIGHTBRACKET) {
                    throw new SintaxErrorException("Sintax Error");
                }
                lex.next();
                return e;
            }
        },
        function(Token.TokenType.FUNCTION) {
            public double apply(final Lexer lex) throws SintaxErrorException {
                Function f = Function.buildFunction(lex.currentToken().getValue().toUpperCase());
                lex.next();
                lex.next();
                double e = calculateExpr(lex);
                if (lex.currentToken().getTokenType() != Token.TokenType.RIGHTBRACKET) {
                    throw new SintaxErrorException("Sintax Error");
                }
                lex.next();
                return f.apply(e);
            }
        };


        private Token.TokenType tokenType;
        private Lexer lex;

        AtomCalculator(final Token.TokenType tokenType) {
            this.tokenType = tokenType;
        }
        public static AtomCalculator buildAtomElement(final Lexer lex) {

            Token currentToken = lex.currentToken();
            for (AtomCalculator atomElement : AtomCalculator.values()) {
                if (currentToken.getTokenType() == atomElement.tokenType) {
                    return atomElement;
                }
            }
            return null;
        }

        public abstract double apply(final Lexer lex) throws SintaxErrorException;

    }

    static double calculateAtom(final Lexer lex) throws SintaxErrorException {
        AtomCalculator atomElement = AtomCalculator.buildAtomElement(lex);
        return atomElement.apply(lex);
    }

    static double calculatePower(final Lexer lex) throws SintaxErrorException {
        double x = calculateAtom(lex);
        while (lex.currentToken().getTokenType() != Token.TokenType.NONE
                && lex.currentToken().getPriority() == Constants.THIRD_PRIORITY) {
            BinaryOperator h = BinaryOperator.buildOperator(lex.currentToken().getValue());
            lex.next();
            x = h.apply(x, calculatePower(lex));
        }
        return x;
    }

    static double calculateTerm(final Lexer lex) throws SintaxErrorException {
        double x = calculatePower(lex);
        while (lex.currentToken().getTokenType() != Token.TokenType.NONE
                && lex.currentToken().getPriority() == Constants.SECOND_PRIORITY) {
            BinaryOperator h = BinaryOperator.buildOperator(lex.currentToken().getValue());
            lex.next();
            x = h.apply(x, calculatePower(lex));
        }
        return x;
    }

    static double calculateExpr(final Lexer lex) throws SintaxErrorException {
        double x;
        x = calculateTerm(lex);
        while (lex.currentToken().getTokenType() != Token.TokenType.NONE
                && lex.currentToken().getPriority() == Constants.FIRST_PRIORITY) {
            BinaryOperator h = BinaryOperator.buildOperator(lex.currentToken().getValue());
            lex.next();
            x = h.apply(x, calculateTerm(lex));
        }
        return x;
    }

}
