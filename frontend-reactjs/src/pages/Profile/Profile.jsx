import "./Profile.css";

// Hooks
import { useState, useEffect } from "react";

import { Link } from "react-router-dom";

// Biblioteca axios
import axios from "axios";

const Profile = () => {
  const [user, setUser] = useState({});
  const [posts, setPosts] = useState([]);
  const [error, setError] = useState(null);

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
        setUser(userData);
      })
      .catch((error) => {
        setError(error.message);
      });
  }, []);

  // Load posts
  useEffect(() => {
    const token = localStorage.getItem("token");
    axios
      .get("http://localhost:8080/posts/me", {
        headers: {
          Authorization: `Bearer ${token}`,
        },
      })
      .then((response) => {
        const postsData = response.data;
        setPosts(postsData);
      })
      .catch((error) => {
        setError(error.message);
      });
  }, []);

  return (
    <div id="profile">
      <h2>Perfil</h2>
      <div className="profile-info">
        <img src={user.profilePicture} alt={user.fullName} />
        <h3>{user.fullName}</h3>
        <p>@{user.username}</p>
        <p>Seguidores: {user.followers}</p>
        <p>Seguindo: {user.following}</p>
      </div>
      <div className="posts">
        {posts.map((post) => (
          <div key={post.id} className="post">
            <img src={post.image} alt={post.description} />
            <p>{post.description}</p>
          </div>
        ))}
      </div>
      <Link to="/edit-profile">
        <button>Editar Perfil</button>
      </Link>
      {error && <p style={{ color: "red" }}>{error}</p>}
    </div>
  );
};

export default Profile;
