package ru.yandex.automationtools2016.calculator;


enum BinaryOperator {
    ADD("+") {
        @Override
        public double apply(final double leftOperand, final double rightOperand) {
            return leftOperand + rightOperand;
        }
    },
    SUB("-") {
        @Override
        public double apply(final double leftOperand, final double rightOperand) {
            return leftOperand - rightOperand;
        }
    },
    MULT("*") {
        @Override
        public double apply(final double leftOperand, final double rightOperand) {
            return leftOperand * rightOperand;
        }
    },
    DIV("/") {
        @Override
        public double apply(final double leftOperand, final double rightOperand) {
            if (Math.abs(rightOperand) < Constants.EPS) {
                throw new ArithmeticException("Division by zero");
            }
            return leftOperand / rightOperand;
        }
    },
    POWER("^") {
        @Override
        public double apply(final double leftOperand, final double rightOperand) {
            return Math.pow(leftOperand, rightOperand);
        }
    },
    NONE("~") {
        @Override
        public double apply(final double leftOperand, final double rightOperand) {
            return leftOperand;
        }
    };

    private final String code;

    private BinaryOperator(final String code) {
        this.code = code;
    }

    public static BinaryOperator buildOperator(final String code) {
        for (BinaryOperator binaryOperator : BinaryOperator.values()) {
            if (code.equals(binaryOperator.code)) {
                return binaryOperator;
            }
        }
        return null;
    }

    public boolean isOperatorEquals(final BinaryOperator... binaryOperators) {
        for (BinaryOperator binaryOperator : binaryOperators) {
            if (this.equals(binaryOperator)) {
                return true;
            }
        }
        return false;
    }

    public abstract double apply(final double leftOperand, final double rightOperand);
}
