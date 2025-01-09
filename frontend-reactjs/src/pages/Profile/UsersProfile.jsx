import "./Profile.css";
import { useState, useEffect } from "react";
import { useParams } from "react-router-dom";
import axios from "axios";

const UserProfile = () => {
  const { objectId } = useParams();
  const [userProfile, setUserProfile] = useState({});
  const [posts, setPosts] = useState([]);
  const [error, setError] = useState(null);

  const defaultProfileImage =
    "https://www.nicepng.com/png/detail/128-1280406_view-user-icon-png-user-circle-icon-png.png"; // Substitua pelo URL da sua imagem padrão

  // Load user data
  useEffect(() => {
    const token = localStorage.getItem("token");
    axios
      .get(`http://localhost:8080/profiles/${objectId}`, {
        headers: {
          Authorization: `Bearer ${token}`,
        },
      })
      .then((response) => {
        const userData = response.data;
        console.log(userData); // Log the response data to verify
        setUserProfile(userData);
      })
      .catch((error) => {
        setError(error.message);
      });
  }, [objectId]);

  // Load posts
  useEffect(() => {
    const token = localStorage.getItem("token");
    axios
      .get(`http://localhost:8080/posts/${objectId}`, {
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
  }, [objectId]);

  return (
    <div id="profile">
      <div className="profile-header">
        <img
          src={userProfile.profileImage || defaultProfileImage}
          alt="Profile"
          className="profile-image"
        />
        <div className="profile-info-wrapper">
          <div className="profile-info">
            <div className="profile-username">
              <h3>@{userProfile.username}</h3>
            </div>
            <div className="stats">
              <div>
                <span>{userProfile.followers}</span>
                <p>Seguidores</p>
              </div>
              <div>
                <span>{userProfile.following}</span>
                <p>Seguindo</p>
              </div>
            </div>
          </div>
          <div className="name-bio">
            <span className="name">{userProfile.user?.name}</span>
            <p className="bio">{userProfile.bio}</p>
          </div>
        </div>
      </div>
      <div className="photos-container">
        {posts.map((post) => (
          <div key={post.id} className="photo">
            <img src={post.imageURL} alt={post.title} />
          </div>
        ))}
      </div>
      {error && <p style={{ color: "red" }}>{error}</p>}
    </div>
  );
};

export default UserProfile;
