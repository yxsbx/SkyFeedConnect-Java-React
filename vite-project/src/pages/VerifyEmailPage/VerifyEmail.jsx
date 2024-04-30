import { useState, useEffect } from "react";
import axios from "axios";
import { useLocation } from "react-router-dom";
import "./verifyEmail.css";
import FireworksCanvas from "./FireworksCanvas ";

const VerifyEmail = () => {
  const [message, setMessage] = useState("");
  const location = useLocation();
  const uuid = location.pathname.split("/").pop(); // Extrai o uuid do URL

  useEffect(() => {
    // Função para requisitar ativação de usuário ao backend
    const verifyEmail = async () => {
      setMessage("Verificando email...");
      try {
        const response = await axios.get(
          `http://localhost:8080/users/activate/${uuid}`
        );
        setMessage(response.data.message);
      } catch (error) {
        console.error("Erro ao verificar email:", error);
        setMessage(error.message);
      }
    };

    verifyEmail();
  }, []);

  return (
    <div id="verify-page">
      <h1 className="title-verify">{message}</h1>
      <FireworksCanvas />
    </div>
  );
};

export default VerifyEmail;
