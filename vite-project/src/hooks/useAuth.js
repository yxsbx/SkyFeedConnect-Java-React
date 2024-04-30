import { useState } from "react";
import { useNavigate } from "react-router-dom";
import { login } from "../services/authService";

const useAuth = () => {
  const navigate = useNavigate();
  const [error, setError] = useState("");
  const [isSubmitting, setIsSubmitting] = useState(false);

  const handleSignIn = async (username, password) => {
    setIsSubmitting(true);
    try {
      const response = await login(username, password);
      const { token } = response.data;
      localStorage.setItem("userToken", token);
      localStorage.setItem("userName", username.split("@")[0]);
      navigate("/main");
    } catch (error) {
      setError("Erro ao fazer login. Verifique suas credenciais!");
    } finally {
      setIsSubmitting(false);
    }
  };

  return { handleSignIn, error, isSubmitting };
};

export default useAuth;
