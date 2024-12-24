import "./Auth.css";

// Components
import { Link } from "react-router-dom";

// Hooks
import { useState } from "react";

// Biblioteca axios
import axios from "axios";

const Register = () => {
  const [name, setName] = useState("");
  const [username, setUsername] = useState("");
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const [error, setError] = useState(null);

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
        axios
          .post("http://localhost:8080/auth/login", {
            username: user.username,
            password: user.password,
          })
          .then((response) => {
            const token = response.data;
            if (token) {
              localStorage.setItem("token", token);
              axios.defaults.headers.common[
                "Authorization"
              ] = `Bearer ${token}`;
              window.location.href = "/home"; // Redireciona para a página de home e recarrega
            } else {
              setError("Erro ao fazer login");
            }
          })
          .catch((error) => {
            setError(error.message);
          });
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
