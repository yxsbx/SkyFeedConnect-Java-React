import "./LoginPage.css";
import useAuth from "../../hooks/useAuth";
import { useState } from "react";
import { useNavigate } from "react-router-dom";

const LoginPage = () => {
  const navigate = useNavigate();
  const { handleSignIn, error, isSubmitting } = useAuth();
  const [username, setUsername] = useState("");
  const [password, setPassword] = useState("");

  const handleSubmit = (event) => {
    event.preventDefault();
    handleSignIn(username, password);
  };

  const handleSignUpClick = () => {
    navigate("/signup");
  };

  return (
    <div id="login-page">
      <div className="sign-in-container">
        <h1 className="title-sign">Sign In</h1>
        <p className="subtitle">Welcome to Sky Feed Connect!</p>
        <form onSubmit={handleSubmit} className="sign-in-form">
          <input
            type="email"
            placeholder="Email"
            value={username}
            onChange={(e) => setUsername(e.target.value)}
            required
          />
          <input
            type="password"
            placeholder="Password"
            value={password}
            onChange={(e) => setPassword(e.target.value)}
            required
          />
          <button
            type="submit"
            className="sign-in-button"
            disabled={isSubmitting}
          >
            {isSubmitting ? "Signing In..." : "Sign In"}
          </button>
        </form>
        {error && (
          <p className="error-message" style={{ color: "red" }}>
            {error}
          </p>
        )}
        <p className="sign-in-redirect">
          Not a Member yet?{" "}
          <span className="sign-up-link" onClick={handleSignUpClick}>
            Sign up
          </span>
        </p>
      </div>
    </div>
  );
};

export default LoginPage;
