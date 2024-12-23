import "./Auth.css";

// Components
import { Link, useNavigate } from "react-router-dom";

// Hooks
import { useState, useEffect } from "react";

// Biblioteca axios
import axios from "axios";

const Register = () => {
  const [name, setName] = useState("");
  const [username, setUsername] = useState("");
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const [error, setError] = useState(null);
  const navigate = useNavigate();

  const handleSubmit = (e) => {
    e.preventDefault();

    const user = {
      name,
      username,
      email,
      password,
    };

    axios
      .post("http://localhost:8080/users", user)
      .then((response) => {
        console.log(response.data);
        // Limpar os campos de input
        setName("");
        setUsername("");
        setEmail("");
        setPassword("");

        navigate("/login");
      })
      .catch((error) => {
        setError(error.message);
      });
  };

  return (
    <div id="register">
      <h2>Social Network</h2>
      <p className="subtitle">Cadastre-se para ver as fotos dos seus amigos.</p>
      <form onSubmit={handleSubmit}>
        <input
          type="text"
          placeholder="Nome"
          value={name}
          onChange={(e) => setName(e.target.value)}
        />
        <input
          type="text"
          placeholder="Nome de usuário"
          value={username}
          onChange={(e) => setUsername(e.target.value)}
        />
        <input
          type="email"
          placeholder="E-mail"
          value={email}
          onChange={(e) => setEmail(e.target.value)}
        />
        <input
          type="password"
          placeholder="Senha"
          value={password}
          onChange={(e) => setPassword(e.target.value)}
        />
        <input type="submit" value="Cadastrar" />
        {error && <p style={{ color: "red" }}>{error}</p>}
      </form>
      <p>
        Já tem conta? <Link to="/login">Entrar</Link>
      </p>
    </div>
  );
};

export default Register;
