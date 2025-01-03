import "./EditProfile.css";

// Hooks
import { useState, useEffect } from "react";

import { Link } from "react-router-dom";

// Biblioteca axios
import axios from "axios";

const EditProfile = () => {
  const [fullName, setFullName] = useState("");
  const [username, setUsername] = useState("");
  const [email, setEmail] = useState("");
  const [error, setError] = useState(null);
  const [message, setMessage] = useState(null);

  // Load user data
  useEffect(() => {
    const token = localStorage.getItem("token");
    axios
      .get("http://localhost:8080/users/me", {
        headers: {
          Authorization: `Bearer ${token}`,
        },
      })
      .then((response) => {
        const user = response.data;
        setFullName(user.fullName);
        setUsername(user.username);
        setEmail(user.email);
      })
      .catch((error) => {
        setError(error.message);
      });
  }, []);

  const handleSubmit = async (e) => {
    e.preventDefault();

    // Gather user data from states
    const userData = {
      fullName,
      username,
    };

    const token = localStorage.getItem("token");

    await axios
      .put("http://localhost:8080/users/me", userData, {
        headers: {
          Authorization: `Bearer ${token}`,
          "Content-Type": "application/json",
        },
      })
      .then((response) => {
        console.log(response);
        setMessage("Perfil atualizado com sucesso!");
      })
      .catch((error) => {
        setError(error.message);
      });
  };

  return (
    <div id="edit-profile">
      <h2>Edite seus dados</h2>
      <form onSubmit={handleSubmit}>
        <label>
          <span>Nome:</span>
          <input
            type="text"
            value={fullName}
            onChange={(e) => setFullName(e.target.value)}
            placeholder={fullName || "Nome"}
          />
        </label>
        <label>
          <span>Nome de usuário:</span>
          <input
            type="text"
            value={username}
            onChange={(e) => setUsername(e.target.value)}
            placeholder={username || "Username"}
          />
        </label>
        <label>
          <span>Email:</span>
          <input
            type="email"
            value={email}
            disabled
            onChange={(e) => setEmail(e.target.value)}
            placeholder={email || "Email"}
          />
        </label>
        <label>
          <p>
            Redefinir senha: <Link to="*">Enviar email</Link>
          </p>
        </label>
        <input type="submit" value="Atualizar" />
        {error && <p style={{ color: "red" }}>{error}</p>}
        {message && <p style={{ color: "green" }}>{message}</p>}
      </form>
    </div>
  );
};

export default EditProfile;
