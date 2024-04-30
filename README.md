# SFCAuthenticator

## Overview
O `SFCAuthenticator` é responsável pela autenticação de usuários e gerenciamento de acessos utilizando tokens JSON Web (JWT). Este módulo assegura que as transações e acessos sejam realizados de maneira segura e eficiente.

## Tecnologias Utilizadas

**Spring Boot -** Framework para desenvolvimento de aplicações Java com microserviços.
**Spring Security -** Framework de segurança para autenticação e proteção de aplicações.
**JWT (Json Web Token) -** Padrão de token para autenticação entre duas partes.
**JavaMailSender -** Interface do Spring para envio de emails.
**Lombok -** Biblioteca que reduz código boilerplate em Java.

## Configuração e Instalação

**Pré-requisitos:**

- **Java JDK 11 ou superior.**
- **Maven para gestão de dependências e build do projeto.**
- **Um servidor SMTP configurado para envio de e-mails.**

**Clonar o Repositório:**

``` bash
git clone [URL do repositório]
cd SFCAuthenticator
```

**Configurar Variáveis de Ambiente:**

- **JWT_SECRET:** Chave secreta para assinatura dos tokens JWT.
- **JWT_EXPIRATION_MS:** Tempo de expiração dos tokens JWT em milissegundos.
- **Configurações de SMTP para spring.mail.**

*Executar o Projeto:*

``` bash
mvn spring-boot:run
```

## Funcionalidades

### Autenticação de Usuários

- **Endpoint:** `POST /auth/login`
- **Descrição:** Autentica usuários e retorna um token JWT para acesso.
- **Payload de requisição:**

  ```json
  {
    "username": "nomeDeUsuario",
    "password": "senhaDeUsuario"
  }
  ```

- **Resposta esperada:**

  ```json
  {
    "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9..."
  }
  ```

### Gestão de Usuários
Funcionalidades para registrar e gerenciar usuários no sistema.

#### Registro de Usuário
- **Endpoint:** `POST /users/save`
- **Descrição:** Registra um novo usuário no sistema.
- **Payload de requisição:**

  ```json
  {
    "username": "novoUsuario",
    "email": "email@exemplo.com",
    "password": "senhaSegura"
  }
  ```

#### Exclusão de Usuário

- **Endpoint:** `DELETE /users/delete`
- **Descrição:** Remove um usuário existente com base nas credenciais fornecidas.
- **Payload de requisição:**

  ```json
  {
    "username": "usuarioExistente",
    "password": "senhaDoUsuario"
  }
  ```

#### Ativação de Usuário

- **Endpoint:** `GET /users/activate/{uuid}`
- **Descrição:** Ativa um usuário utilizando o UUID fornecido.

## Autenticação e Segurança

- **AuthEntryPointJwt:** Gerencia as tentativas de acesso não autorizado, respondendo com um status HTTP 401 e detalhes do erro em formato JSON.
- **AuthFilterToken:** Filtro que verifica a presença de tokens JWT válidos nas requisições, autenticando usuários com base nesses tokens.
- **JwtUtils:** Fornece métodos para geração, validação e extração de informações de tokens JWT.
- **WebSecurityConfig:** Configurações de segurança da aplicação, incluindo CORS, CSRF, e gestão de sessões.


# SkyFeedConnect API Services

## Overview
Bem-vindo aos serviços da SkyFeedConnect API! Este README fornece uma visão geral dos serviços disponíveis para interagir com APIs externas e gerenciar usuários.

## Serviços

### NewsResponseIBGE_Service

Serviço responsável por buscar dados de notícias da API do IBGE.

#### Funcionalidades:
- **Buscar Notícias:**
  - **Método:** `getNewsIBGE(Integer qtd)`
  - **Descrição:** Recupera uma quantidade especificada das últimas notícias do IBGE.
  - **Parâmetros:**
    - `qtd`: O número de itens de notícias a serem recuperados. Se não especificado, o padrão é 1.
  - **Retorno:** Retorna um `NewsResponseIBGE_DTO` contendo uma lista de itens de notícias, cada um com título, descrição e a primeira imagem associada.

#### Endpoints:

- **Buscar Notícias:**
  - **Método:** `GET`
  - **URL:** `/newsIBGE/{qtd}`
  - **Descrição:** Recupera notícias do IBGE com base na quantidade especificada.
  - **Parâmetros:**
    - `qtd`: A quantidade de itens de notícias a serem recuperados.
  - **Retorno:** Retorna um `NewsResponseIBGE_DTO` contendo dados de notícias do IBGE.


### UserService

Serviço para gerenciar usuários dentro do sistema.

#### Funcionalidades:
- **Adicionar Usuário:**
  - **Método:** `addUser(UserRequestDTO userRequestDTO)`
  - **Descrição:** Adiciona um novo usuário ao sistema usando os dados fornecidos.
  - **Parâmetros:**
    - `userRequestDTO`: Objeto contendo o nome de usuário do usuário.
  - **Ação:** Salva o novo usuário no repositório de dados.

#### Endpoints:

- **Criar Novo Usuário:**
  - **Método:** `POST`
  - **URL:** `/auth/newUser`
  - **Descrição:** Cria um novo usuário com base nos dados fornecidos.
  - **Corpo da Requisição:** `UserRequestDTO`
  - **Retorno:** Retorna um `ResponseEntity` indicando o sucesso da criação do usuário.

### WeatherService

Serviço para recuperar informações meteorológicas da API OpenWeatherMap.

#### Funcionalidades:
- **Recuperar Clima por Cidade:**
  - **Método:** `getWeatherByCity(String city)`
  - **Descrição:** Recupera dados meteorológicos para uma cidade especificada.
  - **Parâmetros:**
    - `city`: O nome da cidade para a qual os dados meteorológicos são solicitados.
  - **Retorno:** Retorna um `WeatherResponseDTO` com informações detalhadas sobre o clima atual na cidade especificada.
  - **Endpoint da API Usado:** `http://api.openweathermap.org/data/2.5/weather?q={city}&appid={apiKey}&lang=pt_br&units=metric`
  - **Chave da API:** A chave da API deve ser configurada no arquivo de propriedades do Spring (`application.properties`) na propriedade `openweather.api.key`.

#### Endpoints:

- **Obter Clima:**
  - **Método:** `GET`
  - **URL:** `/weather/{city}`
  - **Descrição:** Recupera informações meteorológicas para a cidade especificada.
  - **Parâmetros:**
    - `city`: O nome da cidade para a qual os dados meteorológicos são solicitados.
  - **Retorno:** Retorna um `ResponseEntity` contendo informações meteorológicas para a cidade especificada.

## Tecnologias Utilizadas

- Spring Boot
- Spring Web
- RestTemplate for API calls
- Lombok

## Installation and Configuration
Certifique-se de que as chaves da API e outras configurações necessárias estão corretas no arquivo `application.properties` antes de iniciar o serviço. Para executar localmente, use:
```bash
mvn spring-boot:run
  ```

Cada controller contém endpoints específicos com métodos correspondentes para lidar com solicitações recebidas e fornecer respostas apropriadas.

# Segurança JWT

Este pacote contém classes relacionadas à autenticação e segurança JWT (JSON Web Token).

## AuthEntryPointJwt

A classe `AuthEntryPointJwt` atua como o ponto de entrada de autenticação personalizado para autenticação JWT. Ele lida com pontos de entrada não autorizados enviando uma resposta HTTP com corpo JSON.

## AuthFilterToken

A classe `AuthFilterToken` é um filtro personalizado para lidar com autenticação JWT. Ele filtra solicitações HTTP para verificar tokens JWT e autenticar usuários.

## JwtUtils

A classe `JwtUtils` fornece métodos de utilidade para geração, validação e extração de tokens JWT.

### Métodos:

- `extractUsernameFromJwtToken(String token)`: Extrai o nome de usuário de um token JWT.
- `validateJwtToken(String token)`: Valida um token JWT.
- `extractClaimFromJwtToken(String token, Function<Claims, T> claimsResolver)`: Extrai uma reivindicação específica de um token JWT.
- `getSigningKey()`: Gera a chave de assinatura para o token JWT.



Essas classes trabalham juntas para fornecer funcionalidades de autenticação JWT no aplicativo.

