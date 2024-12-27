import "./Auth.css";
import Logo from "../../assets/logo_social.png"

// Components
import { Link } from "react-router-dom";

// Hooks
import { useState } from "react";

// Biblioteca axios
import axios from "axios";

const Login = () => {
  const [username, setUsername] = useState("");
  const [password, setPassword] = useState("");
  const [error, setError] = useState(null);

  const handleSubmit = (e) => {
    e.preventDefault();

    const user = {
      username,
      password,
    };

    axios
      .post("http://localhost:8080/auth/login", user)
      .then((response) => {
        const token = response.data;
        if (token) {
          localStorage.setItem("token", token);
          axios.defaults.headers.common["Authorization"] = `Bearer ${token}`;
          window.location.href = "/";
        } else {
          setError("Token inválido ou expirado");
        }
      })
      .catch((error) => {
        setError(error.message);
      });
  };

  return (
    <div id="login">
      <img src={Logo} alt="Logo" />
      <h2>Social Network</h2>
      <p className="subtitle">
        Acesse sua conta e compartilhe momentos com quem faz parte deles.
      </p>
      <form onSubmit={handleSubmit}>
        <input
          type="text"
          placeholder="Nome de usuário"
          value={username}
          onChange={(e) => setUsername(e.target.value)}
        />
        <input
          type="password"
          placeholder="Senha"
          value={password}
          onChange={(e) => setPassword(e.target.value)}
        />
        <input type="submit" value="Entrar" />
        {error && <p style={{ color: "red" }}>{error}</p>}
      </form>
      <p>
        Não tem conta? <Link to="/register">Cadastrar</Link>
      </p>
    </div>
  );
};

export default Login;
