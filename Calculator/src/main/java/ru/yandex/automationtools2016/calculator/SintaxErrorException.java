package ru.yandex.automationtools2016.calculator;

import java.security.spec.ECField;


public class SintaxErrorException extends Exception {
    public SintaxErrorException() { super(); }
    public SintaxErrorException(String message) { super(message); }
    public SintaxErrorException(String message, Throwable cause) { super(message, cause); }
    public SintaxErrorException(Throwable cause) { super(cause); }

}
