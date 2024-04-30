# SkyFeedConnect API Services

## Overview

Bem-vindo aos serviços da SkyFeedConnect API! Este README fornece uma visão geral dos serviços disponíveis para interagir com APIs externas e gerenciar usuários.

### Serviços

#### NewsResponseIBGE_Service

Serviço responsável por buscar dados de notícias da API do IBGE.

##### Funcionalidades

- **Buscar Notícias:**
  - **Método:** `getNewsIBGE(Integer qtd)`
  - **Descrição:** Recupera uma quantidade especificada das últimas notícias do IBGE.
  - **Parâmetros:**
    - `qtd`: O número de itens de notícias a serem recuperados. Se não especificado, o padrão é 1.
  - **Retorno:** Retorna um `NewsResponseIBGE_DTO` contendo uma lista de itens de notícias, cada um com título, descrição e a primeira imagem associada.

##### Endpoints

- **Buscar Notícias:**
  - **Método:** `GET`
  - **URL:** `/newsIBGE/{qtd}`
  - **Descrição:** Recupera notícias do IBGE com base na quantidade especificada.
  - **Parâmetros:**
    - `qtd`: A quantidade de itens de notícias a serem recuperados.
  - **Retorno:** Retorna um `NewsResponseIBGE_DTO` contendo dados de notícias do IBGE.

##### UserService

Serviço para gerenciar usuários dentro do sistema.

##### Funcionalidades

- **Adicionar Usuário:**
  - **Método:** `addUser(UserRequestDTO userRequestDTO)`
  - **Descrição:** Adiciona um novo usuário ao sistema usando os dados fornecidos.
  - **Parâmetros:**
    - `userRequestDTO`: Objeto contendo o nome de usuário do usuário.
  - **Ação:** Salva o novo usuário no repositório de dados.

##### Endpoints

- **Criar Novo Usuário:**
  - **Método:** `POST`
  - **URL:** `/auth/newUser`
  - **Descrição:** Cria um novo usuário com base nos dados fornecidos.
  - **Corpo da Requisição:** `UserRequestDTO`
  - **Retorno:** Retorna um `ResponseEntity` indicando o sucesso da criação do usuário.

#### WeatherService

Serviço para recuperar informações meteorológicas da API OpenWeatherMap.

##### Funcionalidades

- **Recuperar Clima por Cidade:**

  - **Método:** `getWeatherByCity(String city)`
  - **Descrição:** Recupera dados meteorológicos para uma cidade especificada.
  - **Parâmetros:**
    - `city`: O nome da cidade para a qual os dados meteorológicos são solicitados.
  - **Retorno:** Retorna um `WeatherResponseDTO` com informações detalhadas sobre o clima atual na cidade especificada.
  - **Endpoint da API Usado:** `http://api.openweathermap.org/data/2.5/weather?q={city}&appid={apiKey}&lang=pt_br&units=metric`
  - **Chave da API:** A chave da API deve ser configurada no arquivo de propriedades do Spring (`application.properties`) na propriedade `openweather.api.key`.

##### Endpoints

- **Obter Clima:**

  - **Método:** `GET`
  - **URL:** `/weather/{city}`
  - **Descrição:** Recupera informações meteorológicas para a cidade especificada.
  - **Parâmetros:**
    - `city`: O nome da cidade para a qual os dados meteorológicos são solicitados.
  - **Retorno:** Retorna um `ResponseEntity` contendo informações meteorológicas para a cidade especificada.

##### Tecnologias Utilizadas

- Spring Boot
- Spring Web
- RestTemplate for API calls
- Lombok

##### Installation and Configuration

Certifique-se de que as chaves da API e outras configurações necessárias estão corretas no arquivo `application.properties` antes de iniciar o serviço. Para executar localmente, use:

```bash
mvn spring-boot:run
```

Cada controller contém endpoints específicos com métodos correspondentes para lidar com solicitações recebidas e fornecer respostas apropriadas.

##### Segurança JWT

Este pacote contém classes relacionadas à autenticação e segurança JWT (JSON Web Token).

##### AuthEntryPointJwt

A classe `AuthEntryPointJwt` atua como o ponto de entrada de autenticação personalizado para autenticação JWT. Ele lida com pontos de entrada não autorizados enviando uma resposta HTTP com corpo JSON.

##### AuthFilterToken

A classe `AuthFilterToken` é um filtro personalizado para lidar com autenticação JWT. Ele filtra solicitações HTTP para verificar tokens JWT e autenticar usuários.

##### JwtUtils

A classe `JwtUtils` fornece métodos de utilidade para geração, validação e extração de tokens JWT.

##### Métodos

- `extractUsernameFromJwtToken(String token)`: Extrai o nome de usuário de um token JWT.
- `validateJwtToken(String token)`: Valida um token JWT.
- `extractClaimFromJwtToken(String token, Function<Claims, T> claimsResolver)`: Extrai uma reivindicação específica de um token JWT.
- `getSigningKey()`: Gera a chave de assinatura para o token JWT.

Essas classes trabalham juntas para fornecer funcionalidades de autenticação JWT no aplicativo.

---

# SkyFeedConnect API Services - EN Version

## Overview

Welcome to the SkyFeedConnect API services! This README provides an overview of the available services for interacting with external APIs and managing users.

### Services

#### NewsResponseIBGE_Service

Service responsible for fetching news data from the IBGE API.

##### Features

- **Fetch News:**
  - **Method:** `getNewsIBGE(Integer qtd)`
  - **Description:** Retrieves a specified number of latest news from IBGE.
  - **Parameters:**
    - `qtd`: The number of news items to retrieve. Defaults to 1 if not specified.
  - **Return:** Returns a `NewsResponseIBGE_DTO` containing a list of news items, each with title, description, and the first associated image.

##### Endpoints

- **Fetch News:**
  - **Method:** `GET`
  - **URL:** `/newsIBGE/{qtd}`
  - **Description:** Fetches news from IBGE based on the specified quantity.
  - **Parameters:**
    - `qtd`: The quantity of news items to retrieve.
  - **Return:** Returns a `NewsResponseIBGE_DTO` containing news data from IBGE.

##### UserService

Service for managing users within the system.

##### Features

- **Add User:**
  - **Method:** `addUser(UserRequestDTO userRequestDTO)`
  - **Description:** Adds a new user to the system using the provided data.
  - **Parameters:**
    - `userRequestDTO`: Object containing the user's username.
  - **Action:** Saves the new user in the data repository.

##### Endpoints

- **Create New User:**
  - **Method:** `POST`
  - **URL:** `/auth/newUser`
  - **Description:** Creates a new user based on the provided data.
  - **Request Body:** `UserRequestDTO`
  - **Return:** Returns a `ResponseEntity` indicating the success of user creation.

#### WeatherService

Service for retrieving weather information from the OpenWeatherMap API.

##### Features

- **Fetch Weather by City:**

  - **Method:** `getWeatherByCity(String city)`
  - **Description:** Retrieves weather data for a specified city.
  - **Parameters:**
    - `city`: The city name for which weather data is requested.
  - **Return:** Returns a `WeatherResponseDTO` with detailed information about the current weather in the specified city.
  - **API Endpoint Used:** `http://api.openweathermap.org/data/2.5/weather?q={city}&appid={apiKey}&lang=pt_br&units=metric`
  - **API Key:** The API key should be configured in the Spring properties file (`application.properties`) under the `openweather.api.key` property.

##### Endpoints

- **Get Weather:**

  - **Method:** `GET`
  - **URL:** `/weather/{city}`
  - **Description:** Retrieves weather information for the specified city.
  - **Parameters:**
    - `city`: The city name for which weather data is requested.
  - **Return:** Returns a `ResponseEntity` containing weather information for the specified city.

##### Technologies Used

- Spring Boot
- Spring Web
- RestTemplate for API calls
- Lombok

##### Installation and Configuration

Ensure that API keys and other necessary configurations are correct in the `application.properties` file before starting the service. To run locally, use:

```bash
mvn spring-boot:run
```

Each controller contains specific endpoints with corresponding methods to handle incoming requests and provide appropriate responses.

##### JWT Security

This package contains classes related to JWT (JSON Web Token) authentication and security.

##### AuthEntryPointJwt

The `AuthEntryPointJwt` class acts as a custom authentication entry point for JWT authentication. It handles unauthorized entry points by sending an HTTP response with a JSON body.

##### AuthFilterToken

The `AuthFilterToken` class is a custom filter to handle JWT authentication. It filters HTTP requests to verify JWT tokens and authenticate users.

##### JwtUtils

The `JwtUtils` class provides utility methods for generating, validating, and extracting JWT tokens.

##### Methods

- `extractUsernameFromJwtToken(String token)`: Extracts the username from a JWT token.
- `validateJwtToken(String token)`: Validates a JWT token.
- `extractClaimFromJwtToken(String token, Function<Claims, T> claimsResolver)`: Extracts a specific claim from a JWT token.
- `getSigningKey()`: Generates the signing key for the JWT token.

These classes work together to provide JWT authentication functionalities in the application.
