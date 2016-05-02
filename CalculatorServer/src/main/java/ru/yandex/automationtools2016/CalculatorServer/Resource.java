package ru.yandex.automationtools2016.CalculatorServer;
import ru.yandex.automationtools2016.calculator.SimpleCalculator;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.json.Json;
import javax.json.JsonObject;

@Path("calculate")
@Produces(MediaType.APPLICATION_JSON)

public class Resource {
    SimpleCalculator calculator = new SimpleCalculator();

    @GET
    public String helloWorld(@QueryParam("query") String query) {
        double ans;
        try {
            //System.out.println(query);
            ans = calculator.calculate(query);
            //System.out.println(ans);
        }
        catch (Exception e) {
            JsonObject response = Json.createObjectBuilder().add("Result", "Error")
                    .add("Query", query).add("ErrorMessage", e.getMessage()).build();
            return response.toString();
        }
        JsonObject response = Json.createObjectBuilder().add("Result", "Ok")
                .add("Query", query)
                .add("Answer", ans).build();

        return response.toString();
    }
}