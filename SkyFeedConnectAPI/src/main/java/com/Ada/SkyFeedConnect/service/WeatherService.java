package com.Ada.SkyFeedConnect.service;

import com.Ada.SkyFeedConnect.dto.WeatherResponseDTO;
import com.Ada.SkyFeedConnect.model.WeatherInfo;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * Service class for fetching weather data from OpenWeatherMap API.
 */
@Service
public class WeatherService {
    @Value("${openweather.api.key}")
    private String apiKey;

    /**
     * Retrieves weather data for a specific city from the OpenWeatherMap API.
     *
     * @param city The name of the city for which weather data is requested.
     * @return A DTO containing weather data for the specified city.
     */
    public WeatherResponseDTO getWeatherByCity(String city) {
        String url = "http://api.openweathermap.org/data/2.5/weather?q=" + city + "&appid=" + apiKey + "&lang=pt_br&units=metric";
        RestTemplate restTemplate = new RestTemplate();
        WeatherInfo weatherData = restTemplate.getForObject(url, WeatherInfo.class);

        return WeatherResponseDTO.toDTO(weatherData);
    }
}
