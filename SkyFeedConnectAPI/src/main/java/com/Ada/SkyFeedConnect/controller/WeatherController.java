package com.Ada.SkyFeedConnect.controller;

import com.Ada.SkyFeedConnect.dto.WeatherResponseDTO;
import com.Ada.SkyFeedConnect.service.WeatherService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@CrossOrigin
public class WeatherController {

  private final WeatherService weatherService;

    /**
     * Retrieves weather information for the specified city.
     *
     * @param city The name of the city for which weather data is requested.
     * @return A ResponseEntity containing weather data for the specified city.
     */

  @GetMapping("/weather/{city}")
  public ResponseEntity<WeatherResponseDTO> getWeather(@PathVariable String city) {
    return ResponseEntity.ok(weatherService.getWeatherByCity(city));
  }
}

