package ru.yandex.automationtools2016.CalculatorClient;

import com.google.gson.annotations.SerializedName;

public class CalculatorResponse {
    @SerializedName("Result")
    public String result;
    @SerializedName("Query")
    public String query;
    @SerializedName("ErrorMessage")
    public String errorMessage;
    @SerializedName("Answer")
    public Double answer;
}
