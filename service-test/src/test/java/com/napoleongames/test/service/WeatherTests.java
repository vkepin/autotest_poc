package com.napoleongames.test.service;

import com.napoleongames.test.service.client.WeatherService;
import com.napoleongames.test.service.model.WeatherResponse;

import org.apache.http.HttpStatus;
import org.assertj.core.api.Assertions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class WeatherTests
{
    private static final Logger LOGGER = LoggerFactory.getLogger(WeatherTests.class);
    private final WeatherService weatherService = WeatherService.withDefaultSpec();

    @Test(dataProvider = "cityProvider")
    public void testWeather(String city, String expectedWeather)
    {
        LOGGER.info("Send request");
        WeatherResponse weatherResponse = weatherService.getWeather(city)
                                                        .then()
                                                        .statusCode(HttpStatus.SC_OK)
                                                        .extract()
                                                        .as(WeatherResponse.class);
        LOGGER.info("validate response");
        Assertions.assertThat(weatherResponse.getWeather()).isEqualToIgnoringCase(expectedWeather);
    }

    @DataProvider
    public Object[][] cityProvider() {
        return new Object[][] {
            { "Paris", "Paris: sunny: enjoy" },
            { "London", "London: raining: a light drizzle"},
            { "Madrid", "Sorry, I couldn't fetch the weather for you :("},
        };
    }

}
