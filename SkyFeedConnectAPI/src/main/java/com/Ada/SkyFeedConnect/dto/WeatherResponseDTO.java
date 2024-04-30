package com.Ada.SkyFeedConnect.dto;

import com.Ada.SkyFeedConnect.model.WeatherInfo;

/**
 * DTO class representing weather response data.
 */

public record WeatherResponseDTO(String description, String icon, double temp, double feels_like, String name) {

    /**
     * Converts WeatherInfo data to a WeatherResponseDTO object.
     *
     * @param weatherData The WeatherInfo object containing weather data.
     * @return A WeatherResponseDTO object populated with data from WeatherInfo.
     */
    public static WeatherResponseDTO toDTO(WeatherInfo weatherData) {
        WeatherInfo.Weather weather = weatherData.getWeather().getFirst();
        WeatherInfo.Main main = weatherData.getMain();
        return new WeatherResponseDTO(weather.description(), weather.icon(), main.temp(), main.feels_like(), weatherData.getName());
    }
}