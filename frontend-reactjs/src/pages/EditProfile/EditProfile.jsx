import "./EditProfile.css";
import "@fortawesome/fontawesome-free/css/all.min.css";

// Hooks
import { useState, useEffect } from "react";
import { Link } from "react-router-dom";

// Biblioteca axios
import axios from "axios";

const EditProfile = () => {
  const [userProfile, setUserProfile] = useState({
    profileImage: "",
    name: "",
    username: "",
    email: "",
    phoneNumber: "",
  });
  const [error, setError] = useState(null);
  const [message, setMessage] = useState(null);

  const defaultProfileImage =
    "https://www.nicepng.com/png/detail/128-1280406_view-user-icon-png-user-circle-icon-png.png"; // Substitua pelo URL da sua imagem padrão

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
        const userData = response.data;
        setUserProfile({
          profileImage: userData.profileImage || defaultProfileImage,
          name: userData.user?.name || "",
          username: userData.username || "",
          email: userData.user?.email || "",
          phoneNumber: userData.user?.phoneNumber || "",
        });
      })
      .catch((error) => {
        setError(error.message);
      });
  }, []);

  const handleSubmit = async (e) => {
    e.preventDefault();

    const token = localStorage.getItem("token");

    await axios
      .put("http://localhost:8080/profiles/me", userProfile, {
        headers: {
          Authorization: `Bearer ${token}`,
          "Content-Type": "application/json",
        },
      })
      .then((response) => {
        console.log(response.data);
        setMessage("Perfil atualizado com sucesso!");
      })
      .catch((error) => {
        setError(error.message);
      });
  };

  const handleProfilePictureChange = (e) => {
    const file = e.target.files[0];
    const reader = new FileReader();
    reader.onloadend = () => {
      setUserProfile((prevProfile) => ({
        ...prevProfile,
        profileImage: reader.result,
      }));
    };
    if (file) {
      reader.readAsDataURL(file);
    }
  };

  const handleChange = (e) => {
    const { name, value } = e.target;
    setUserProfile((prevProfile) => ({
      ...prevProfile,
      [name]: value,
    }));
  };

  return (
    <div id="edit-profile">
      <h2>Edite seus dados</h2>
      <form onSubmit={handleSubmit}>
        <div className="profile-picture">
          <img
            src={userProfile.profileImage || defaultProfileImage}
            alt="Profile"
            className="circular-image"
          />
          <label htmlFor="file-upload" className="custom-file-upload">
            <i className="fas fa-pencil-alt"></i>
          </label>
          <input
            id="file-upload"
            type="file"
            accept="image/*"
            onChange={handleProfilePictureChange}
          />
        </div>
        <label>
          <span>Nome:</span>
          <input
            type="text"
            name="name"
            defaultValue={userProfile.name}
            onChange={handleChange}
            placeholder="Nome"
          />
        </label>
        <label>
          <span>Nome de usuário:</span>
          <input
            type="text"
            name="username"
            defaultValue={userProfile.username}
            onChange={handleChange}
            placeholder="Nome de usuário"
          />
        </label>
        <label>
          <span>Email:</span>
          <input
            type="email"
            name="email"
            defaultValue={userProfile.email}
            onChange={handleChange}
            placeholder="Email"
          />
        </label>
        <label>
          <span>Número de telefone:</span>
          <input
            type="tel"
            name="phoneNumber"
            defaultValue={userProfile.phoneNumber}
            onChange={handleChange}
            placeholder="Número de telefone"
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
