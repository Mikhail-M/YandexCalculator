package ru.yandex.automationtools2016.CalculatorClient;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class CalculatorClient {
    CalculatorAPI serviceConnect;

    public CalculatorClient(final String url) {
        Retrofit retrofit = new Retrofit.Builder().baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        serviceConnect = retrofit.create(CalculatorAPI.class);
    }

    public Response <CalculatorResponse> calculate(String query) throws Exception {
        Call<CalculatorResponse> req = serviceConnect.getAnswer(query);

        return req.execute();
    }

}
