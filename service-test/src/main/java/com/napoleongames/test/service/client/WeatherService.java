package com.napoleongames.test.service.client;

import static io.restassured.RestAssured.given;

import com.epam.reportportal.annotations.Step;
import com.napoleongames.test.service.core.spec.RequestSpecificationProvider;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import lombok.AllArgsConstructor;

@AllArgsConstructor(staticName = "withSpec")
public class WeatherService
{
    private static final String GET_WEATHER = "/weather/{city}";
    private static final String HELLO = "/hello";

    private RequestSpecification requestSpecification;

    public static WeatherService withDefaultSpec()
    {
        return WeatherService.withSpec(RequestSpecificationProvider.defaultSpec());
    }

    public Response getHello()
    {
        return given()
            .spec(requestSpecification)
            .when()
            .get(HELLO)
            .andReturn();
    }

    @Step("Weather Service: Get weather for {city}")
    public Response getWeather(String city)
    {
        return given()
            .spec(requestSpecification)
            .pathParam("city", city)
            .when()
            .get(GET_WEATHER)
            .andReturn();
    }
}
