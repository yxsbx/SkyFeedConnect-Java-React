package com.Ada.SkyFeedConnect.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;

import java.util.List;

/**
 * Represents weather information including weather conditions and temperature.
 */
@Getter
public class WeatherInfo {

    @JsonIgnoreProperties(ignoreUnknown = true)
    private List<Weather> weather;
    private Main main;
    private String name;

    /**
     * Represents weather conditions.
     */
    @JsonIgnoreProperties(ignoreUnknown = true)
    public record Weather(String description, String icon) {}

    /**
     * Represents main weather data such as temperature and feels-like temperature.
     */
    @JsonIgnoreProperties(ignoreUnknown = true)
    public record Main(double temp, double feels_like) {}
}
