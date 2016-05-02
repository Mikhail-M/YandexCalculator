package ru.yandex.automationtools2016.calculator;

enum Function {
    SIN("SIN") {
        @Override
        public double apply(final double operand) {
            return Math.sin(operand);
        }
    },
    COS("COS") {
        @Override
        public double apply(final double operand) {
            return Math.cos(operand);
        }
    },
    ABS("ABS") {
        @Override
        public double apply(final double operand) {
            return Math.abs(operand);
        }
    };


    private final String name;

    Function(final String name) {
        this.name = name;
    }

    public static Function buildFunction(final String code) {
        for (Function function : Function.values()) {
            if (code.equals(function.name)) {
                return function;
            }
        }
        return null;
    }

    public boolean isOperatorEquals(final Function... functions) {
        for (Function function : functions) {
            if (this.equals(function)) {
                return true;
            }
        }
        return false;
    }

    public abstract double apply(final double leftOperand);
}
