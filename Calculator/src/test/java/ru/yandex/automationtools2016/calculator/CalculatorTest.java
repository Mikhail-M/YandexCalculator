package ru.yandex.automationtools2016.calculator;

import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.*;

import static org.junit.Assert.*;

public class CalculatorTest {

    public static final double DELTA = 1E-8;
    Calculator calculator;


    @Before
    public void getInstance() throws ClassNotFoundException, IOException, IllegalAccessException, InstantiationException {
        Properties prop = new Properties();
        prop.load(CalculatorTest.class.getClassLoader().getResourceAsStream("build.properties"));
        Locale.setDefault(Locale.US);
        calculator = (Calculator) CalculatorTest.class.getClassLoader().loadClass(prop.getProperty("IMPLEMENTATION_CLASS")).newInstance();
    }

    @Test
    public void shouldReturnZero() throws SintaxErrorException {
        assertEquals(0, calculator.calculate(Long.toString(0)), DELTA);
    }

    @Test
    public void shouldReturnSix() throws SintaxErrorException {
        SimpleCalculator simpleCalculator = new SimpleCalculator();
        assertEquals("Should return 6", 6.0, simpleCalculator.calculate("2+2*2"), DELTA);
    }

    @Test
    public void shouldReturnEight() throws SintaxErrorException {
        SimpleCalculator simpleCalculator = new SimpleCalculator();
        assertEquals("Should return 8", 8.0, simpleCalculator.calculate("(2+2)*2"), DELTA);
    }

    @Test
    public void shouldReturnOne() throws SintaxErrorException {
        SimpleCalculator simpleCalculator = new SimpleCalculator();
        assertEquals("Should return 1", 1.0, simpleCalculator.calculate("cos((2-2)*2)"), DELTA);
    }

    @Test
    public void shouldReturnNine() throws SintaxErrorException {
        SimpleCalculator simpleCalculator = new SimpleCalculator();
        assertEquals("Should return 9", 9.0, simpleCalculator.calculate("cos((2-2)*2)*3^2"), DELTA);
    }

}