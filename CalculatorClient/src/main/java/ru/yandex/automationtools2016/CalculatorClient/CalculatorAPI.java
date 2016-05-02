package ru.yandex.automationtools2016.CalculatorClient;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface CalculatorAPI {
    @GET("/calculate")
    Call<CalculatorResponse> getAnswer(
            @Query("query") String query
    );
}
