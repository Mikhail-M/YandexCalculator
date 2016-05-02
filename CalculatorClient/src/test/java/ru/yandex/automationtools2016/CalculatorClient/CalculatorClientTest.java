package ru.yandex.automationtools2016.CalculatorClient;

import org.junit.Before;
import org.junit.Test;
import retrofit2.Response;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class CalculatorClientTest {
    public static final double DELTA = 1E-8;


    CalculatorClient calculatorClient;
    @Before
    public void connect() {
        calculatorClient = new CalculatorClient("http://localhost:2222/");
    }

    @Test
    public void shouldBeOne() throws Exception {
        Response<CalculatorResponse> response = calculatorClient.calculate("1-1*cos(0)+1");
        assertNull(response.body().errorMessage);
        assertThat(response.code(), is(200));
        assertThat(response.body().result, is("Ok"));

        assertEquals(1.0, response.body().answer, DELTA);
    }

    @Test
    public void shouldBeTwo() throws Exception {

        Response<CalculatorResponse> response = calculatorClient.calculate("2^2-1*cos(0)-1");
        assertThat(response.code(), is(200));
        assertThat(response.body().result, is("Ok"));

        assertNull(response.body().errorMessage);
        assertEquals(2.0, response.body().answer, DELTA);
    }


    @Test
    public void shouldBeThree() throws Exception {
        Response<CalculatorResponse> response = calculatorClient.calculate("3-3^2+9");
        assertThat(response.code(), is(200));
        assertThat(response.body().result, is("Ok"));

        assertNull(response.body().errorMessage);
        assertEquals(3.0, response.body().answer, DELTA);
    }

    @Test
    public void shouldBeDivizionByZero() throws Exception {
        Response<CalculatorResponse> response = calculatorClient.calculate("1/0");
        assertThat(response.body().result, is("Error"));

        assertThat(response.body().errorMessage, is("Division by zero"));
    }

}