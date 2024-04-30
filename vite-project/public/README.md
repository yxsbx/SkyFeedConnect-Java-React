# SkyFeedConnect Frontend

## Visão Geral

O projeto SkyFeedConnect é uma aplicação web que oferece uma plataforma para interação com APIs externas, gerenciamento de usuários e exibição de conteúdos como notícias e previsão do tempo.

## Tecnologias Utilizadas

- React.js
- React Router
- Axios para requisições HTTP
- React Toastify para notificações
- Font Awesome para ícones
- ESLint para linting
- Vite.js para build e desenvolvimento rápido

## Estrutura de Pastas

O projeto está organizado da seguinte forma:

- **Assets:** Contém o favicon da aplicação.
- **Components:** Componentes reutilizáveis, incluindo o arquivo `App.jsx` e `PrivateRoute.jsx`.
- **Features:**
  - **DateTime:** Componente para exibição da data e hora atuais.
  - **NewsFeed:** Componente para exibição de notícias.
  - **Weather:** Componente para exibição da previsão do tempo.
- **Hooks:** Contém o hook `useAuth.js` para lidar com a autenticação.
- **Pages:** Páginas da aplicação, incluindo:
  - `LoginPage.jsx`: Formulário de login.
  - `MainPage.jsx`: Página principal após o login, exibindo o feed de notícias e widgets.
  - `SignUpPage.jsx`: Formulário de cadastro de novos usuários.
  - `VerifyEmailPage.jsx`: Página para verificação de email.
- **Services:** Serviços como `authService.js` para gerenciamento de autenticação.
- **Styles:** Arquivos de estilos, incluindo `index.css` para estilos globais.
- **Src:** Arquivos de configuração, incluindo `index.js`.

## Descrição das Páginas

### LoginPage.jsx

- Página para login de usuários.
- Utiliza o hook `useAuth` para autenticação.
- Contém formulário de login e redirecionamentos para cadastro.

![LoginPage.jsx](https://github.com/yxsbx/SkyFeedConnect-Java-React/blob/5e346bcb0888ffe6dfaabbf18fe853441686b9cd/screenshots/LoginPage.png)

### SignUpPage.jsx

- Página para cadastro de novos usuários.
- Faz requisições para registrar novos usuários.
- Utiliza `react-toastify` para exibir mensagens de erro/sucesso.

![SignUpPage.jsx](https://github.com/yxsbx/SkyFeedConnect-Java-React/blob/5e346bcb0888ffe6dfaabbf18fe853441686b9cd/screenshots/SignUpPage.png)

### MainPage.jsx

- Página principal após o login.
- Exibe feed de notícias, previsão do tempo e data/hora.
- Permite logout e redirecionamentos.

![MainPage.jsx](https://github.com/yxsbx/SkyFeedConnect-Java-React/blob/5e346bcb0888ffe6dfaabbf18fe853441686b9cd/screenshots/MainPage.png)

#### DateTimeWidget.jsx

- Componente responsável por exibir a data e hora atuais.
- Utiliza `useState` para gerenciar o estado da data atual.
- Utiliza `useEffect` para atualizar a data a cada segundo.
- Contém funções para formatar a data e hora.
- Renderiza a data e hora formatadas na interface.

#### NewsFeed.jsx

- Componente para exibir um feed de notícias.
- Utiliza `useState` para armazenar as notícias obtidas da API.
- Faz requisições para a API de notícias.
- Exibe as notícias principais, secundárias e adicionais de forma organizada.
- Permite visualizar detalhes de cada notícia ao clicar nos links correspondentes.

#### WeatherWidget.jsx

- Componente para exibir as condições climáticas atuais de um determinado local.
- Utiliza `useState` para armazenar o local inserido pelo usuário.
- Faz requisições para a API de previsão do tempo.
- Exibe informações como temperatura, descrição do clima e ícone correspondente.
- Lida com erros de requisição e exibe uma imagem de erro personalizada em caso de falha na busca.

## Componentes Adicionais

### FireworksCanvas.jsx

- O componente `FireworksCanvas` é responsável por criar e exibir efeitos de fogos de artifício quando o usuário clica na tela.
- Utiliza um canvas HTML5 para renderizar os efeitos visuais.
- Gerencia o estado do tamanho do canvas e as partículas dos fogos de artifício.
- Implementa uma classe `Particle` para representar as partículas dos fogos de artifício, com métodos para desenhar e atualizar as partículas.
- Utiliza eventos de clique para criar os efeitos de fogos de artifício na posição do clique do usuário.
- Este componente adiciona uma interação visual interessante à aplicação.

![EmailVerified](https://github.com/yxsbx/SkyFeedConnect-Java-React/blob/5e346bcb0888ffe6dfaabbf18fe853441686b9cd/screenshots/EmailVerification.png)

### VerifyEmail.jsx

- O componente `VerifyEmail` é responsável por verificar e ativar um usuário através de um email enviado com um UUID único.
- Utiliza o hook `useLocation` para obter o UUID do URL, que é usado para a verificação do email.
- Utiliza o hook `useEffect` para realizar a verificação de email ao montar o componente.
- Renderiza a mensagem de verificação e o componente `FireworksCanvas` para exibir os fogos de artifício ao verificar o email com sucesso.
- Este componente é importante para a funcionalidade de verificação de email e proporciona uma experiência visual agradável ao usuário.

![EmailVerification](https://github.com/yxsbx/SkyFeedConnect-Java-React/blob/5e346bcb0888ffe6dfaabbf18fe853441686b9cd/screenshots/ConfirmationEmail.png)

---

# SkyFeedConnect - Frontend EN Version

## Overview

The SkyFeedConnect project is a web application that provides a platform for interacting with external APIs, managing users, and displaying content such as news and weather forecasts.

## Technologies Used

- React.js
- React Router
- Axios for HTTP requests
- React Toastify for notifications
- Font Awesome for icons
- ESLint for linting
- Vite.js for fast build and development

## Folder Structure

The project is organized as follows:

- **Assets:** Contains the application's favicon.
- **Components:** Reusable components, including `App.jsx` and `PrivateRoute.jsx`.
- **Features:**
  - **DateTime:** Component for displaying current date and time.
  - **NewsFeed:** Component for displaying news.
  - **Weather:** Component for displaying weather forecasts.
- **Hooks:** Contains the `useAuth.js` hook for handling authentication.
- **Pages:** Application pages, including:
  - `LoginPage.jsx`: Login form.
  - `MainPage.jsx`: Main page after login, displaying news feed and widgets.
  - `SignUpPage.jsx`: New user registration form.
  - `VerifyEmailPage.jsx`: Page for email verification.
- **Services:** Services such as `authService.js` for authentication management.
- **Styles:** Style files, including `index.css` for global styles.
- **Src:** Configuration files, including `index.js`.

## Page Descriptions

### LoginPage.jsx

- Page for user login.
- Uses the `useAuth` hook for authentication.
- Contains login form and redirections to registration.

![LoginPage.jsx](https://github.com/yxsbx/SkyFeedConnect-Java-React/blob/5e346bcb0888ffe6dfaabbf18fe853441686b9cd/screenshots/LoginPage.png)

### SignUpPage.jsx

- Page for new user registration.
- Makes requests to register new users.
- Uses `react-toastify` to display error/success messages.

![SignUpPage.jsx](https://github.com/yxsbx/SkyFeedConnect-Java-React/blob/5e346bcb0888ffe6dfaabbf18fe853441686b9cd/screenshots/SignUpPage.png)

### MainPage.jsx

- Main page after login.
- Displays news feed, weather forecast, and date/time.
- Allows logout and redirections.

![MainPage.jsx](https://github.com/yxsbx/SkyFeedConnect-Java-React/blob/5e346bcb0888ffe6dfaabbf18fe853441686b9cd/screenshots/MainPage.png)

### DateTimeWidget.jsx

- Component responsible for displaying current date and time.
- Uses `useState` to manage the current date state.
- Uses `useEffect` to update the date every second.
- Contains functions to format date and time.
- Renders formatted date and time in the interface.

### NewsFeed.jsx

- Component to display a news feed.
- Uses `useState` to store news obtained from the API.
- Makes requests to the news API.
- Displays main, secondary, and additional news in an organized manner.
- Allows viewing details of each news by clicking on corresponding links.

### WeatherWidget.jsx

- Component to display current weather conditions for a specified location.
- Uses `useState` to store the location entered by the user.
- Makes requests to the weather forecast API.
- Displays information such as temperature, weather description, and corresponding icon.
- Handles request errors and displays a custom error image in case of search failure.

## Additional Components

### FireworksCanvas.jsx

- The `FireworksCanvas` component is responsible for creating and displaying fireworks effects when the user clicks on the screen.
- Uses an HTML5 canvas to render visual effects.
- Manages the canvas size state and fireworks particles.
- Implements a `Particle` class to represent fireworks particles, with methods to draw and update particles.
- Uses click events to create fireworks effects at the user's click position.
- This component adds an interesting visual interaction to the application.

![EmailVerified](https://github.com/yxsbx/SkyFeedConnect-Java-React/blob/5e346bcb0888ffe6dfaabbf18fe853441686b9cd/screenshots/EmailVerification.png)

### VerifyEmail.jsx

- The `VerifyEmail` component is responsible for verifying and activating a user via an email sent with a unique UUID.
- Uses the `useLocation` hook to get the UUID from the URL, which is used for email verification.
- Uses the `useEffect` hook to perform email verification on component mount.
- Renders the verification message and the `FireworksCanvas` component to display fireworks when email verification is successful.
- This component is important for email verification functionality and provides a pleasant visual experience for the user.

![EmailVerification](https://github.com/yxsbx/SkyFeedConnect-Java-React/blob/5e346bcb0888ffe6dfaabbf18fe853441686b9cd/screenshots/ConfirmationEmail.png)
