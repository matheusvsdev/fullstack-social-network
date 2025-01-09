import "./Auth.css";
import Logo from "../../assets/logo_social.png";

// Components
import { Link } from "react-router-dom";

// Hooks
import { useState } from "react";

// Biblioteca axios
import axios from "axios";

const clientId = "myclientid";
const clientSecret = "myclientsecret";

const Login = () => {
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const [error, setError] = useState(null);

  const handleSubmit = (e) => {
    e.preventDefault();

    const params = new URLSearchParams();
    params.append("grant_type", "password");
    params.append("username", email);
    params.append("password", password);

    axios({
      method: "post",
      url: "http://localhost:8080/oauth2/token",
      data: params.toString(),
      headers: {
        "Content-Type": "application/x-www-form-urlencoded",
        Authorization: `Basic ${btoa(`${clientId}:${clientSecret}`)}`,
      },
    })
      .then((response) => {
        const token = response.data.access_token;
        if (token) {
          localStorage.setItem("token", token);
          axios.defaults.headers.common["Authorization"] = `Bearer ${token}`;
          window.location.href = "/";
        } else {
          setError("Token inválido ou expirado");
        }
      })
      .catch((error) => {
        console.error(error.response.data);  
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
          placeholder="Email"
          value={email}
          onChange={(e) => setEmail(e.target.value)}
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
