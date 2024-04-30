# SFCAuthenticator

## Introdução

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

**Configurar Variáveis de Ambiente:**

- **JWT_SECRET:** Chave secreta para assinatura dos tokens JWT.
- **JWT_EXPIRATION_MS:** Tempo de expiração dos tokens JWT em milissegundos.
- **Configurações de SMTP para spring.mail.**

_Executar o Projeto:_

```bash
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
    "token": "token here"
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

# SFCAuthenticator - EN Version

## Introduction

The `SFCAuthenticator` is responsible for user authentication and access management using JSON Web Tokens (JWT). This module ensures that transactions and accesses are performed securely and efficiently.

## Technologies Used

- **Spring Boot:** Framework for developing Java applications with microservices.
- **Spring Security:** Security framework for authentication and application protection.
- **JWT (Json Web Token):** Token standard for authentication between two parties.
- **JavaMailSender:** Spring interface for sending emails.
- **Lombok:** Library that reduces boilerplate code in Java.

## Configuration and Installation

**Prerequisites:**

- **Java JDK 11 or higher.**
- **Maven for dependency management and project build.**
- **SMTP server configured for sending emails.**

**Set Environment Variables:**

- **JWT_SECRET:** Secret key for JWT token signing.
- **JWT_EXPIRATION_MS:** JWT token expiration time in milliseconds.
- **SMTP settings for spring.mail.**

_Run the Project:_

```bash
mvn spring-boot:run
```

## Features

### User Authentication

- **Endpoint:** `POST /auth/login`
- **Description:** Authenticates users and returns a JWT token for access.
- **Request Payload:**

  ```json
  {
    "username": "username",
    "password": "userpassword"
  }
  ```

- **Expected Response:**

  ```json
  {
    "token": "token here"
  }
  ```

## User Management

Functionalities for registering and managing users in the system.

#### User Registration

- **Endpoint:** `POST /users/save`
- **Description:** Registers a new user in the system.
- **Request Payload:**

  ```json
  {
    "username": "newUser",
    "email": "email@example.com",
    "password": "securePassword"
  }
  ```

#### User Deletion

- **Endpoint:** `DELETE /users/delete`
- **Description:** Removes an existing user based on the provided credentials.
- **Request Payload:**

  ```json
  {
    "username": "existingUser",
    "password": "userPassword"
  }
  ```

#### User Activation

- **Endpoint:** `GET /users/activate/{uuid}`
- **Description:** Activates a user using the provided UUID.

## Authentication and Security

- **AuthEntryPointJwt:** Manages unauthorized access attempts, responding with an HTTP status 401 and error details in JSON format.
- **AuthFilterToken:** Filter that checks for valid JWT tokens in requests, authenticating users based on these tokens.
- **JwtUtils:** Provides methods for generating, validating, and extracting information from JWT tokens.
- **WebSecurityConfig:** Application security configurations, including CORS, CSRF, and session management.
