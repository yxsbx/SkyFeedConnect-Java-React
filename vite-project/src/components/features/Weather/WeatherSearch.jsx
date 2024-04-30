import { useState } from "react";
import axios from "axios";
import "./WeatherSearch.css";

const WeatherWidget = () => {
  const [local, setLocal] = useState("");
  const [weatherInfo, setWeatherInfo] = useState(null);
  const [error, setError] = useState("");

  const JWT_TOKEN = localStorage.getItem("userToken");

  const handleFormSubmit = async (event) => {
    event.preventDefault();
    setError(null);
    setWeatherInfo(null);

    if (local.length < 3) {
      alert("O local precisa ter, pelo menos, três letras");
      return;
    }

    try {
      const response = await axios.get(
        `http://localhost:3333/weather/${local}`,

        {
          headers: {
            Authorization: `Bearer ${JWT_TOKEN}`,
          },
        }
      );

      const weatherData = {
        temp: Math.round(response.data.temp),
        local: response.data.name,
        description: response.data.description,
        icon: `https://openweathermap.org/img/wn/${response.data.icon}@2x.png`,
      };

      setWeatherInfo(weatherData);
    } catch (erro) {
      const imageUrl = `https://http.cat/${erro.response.data.status}`;
      setError(imageUrl);
      console.log("Aconteceu um erro inesperado na API.", erro);
    }
  };

  return (
    <div id="weather-widget">
      <div id="weather-content">
        <h1 className="title">Condições do Tempo</h1>
        <form onSubmit={handleFormSubmit} className="search-form">
          <input
            type="text"
            value={local}
            onChange={(event) => setLocal(event.target.value)}
            placeholder="Insira o local..."
            className="inputWidget"
          />
          <button type="submit" id="weather-button">
            Buscar Clima
          </button>
        </form>
        {error && <img src={error} alt="Erro" className="error-img" />}
        {weatherInfo && (
          <div className="tempo-data">
            <h2>{weatherInfo.local}</h2>
            <div className="tempo-data-info">
              <img src={weatherInfo.icon} alt="Weather icon" />
              <div>
                <span className="description">{weatherInfo.description}</span>
                <span className="temperature">{weatherInfo.temp} ºC</span>
              </div>
            </div>
          </div>
        )}
      </div>
    </div>
  );
};

export default WeatherWidget;
