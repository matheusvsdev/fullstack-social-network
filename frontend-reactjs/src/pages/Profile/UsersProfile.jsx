import "./Profile.css";
import { useState, useEffect, useCallback } from "react";
import { useParams } from "react-router-dom";
import axios from "axios";

const UserProfile = () => {
  const { objectId } = useParams();
  const [userProfile, setUserProfile] = useState({});
  const [posts, setPosts] = useState([]);
  const [error, setError] = useState(null);
  const [followStatus, setFollowStatus] = useState("NONE");
  const [isRequestPending, setIsRequestPending] = useState(false);

  const defaultProfileImage =
    "https://www.nicepng.com/png/detail/128-1280406_view-user-icon-png-user-circle-icon-png.png";

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
        setUserProfile(userData);
      })
      .catch((error) => {
        setError(error.message);
      });
  }, [objectId]);

  const fetchFollowStatus = useCallback(() => {
    const token = localStorage.getItem("token");
    axios
      .get(`http://localhost:8080/follow/${objectId}/isFollowing`, {
        headers: {
          Authorization: `Bearer ${token}`,
        },
      })
      .then((response) => {
        setFollowStatus(response.data);
      })
      .catch((error) => {
        setError(error.message);
      });
  }, [objectId]);

  useEffect(() => {
    fetchFollowStatus();
  }, [fetchFollowStatus]);

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

  const handleFollow = () => {
    const token = localStorage.getItem("token");
    setIsRequestPending(true);
    axios
      .post(
        `http://localhost:8080/follow/${objectId}`,
        {},
        {
          headers: {
            Authorization: `Bearer ${token}`,
          },
        }
      )
      .then(() => {
        setFollowStatus("PENDING");
        fetchFollowStatus(); // Atualiza o status de seguir após enviar a solicitação
      })
      .catch((error) => {
        setError(error.message);
      })
      .finally(() => {
        setIsRequestPending(false);
      });
  };

  const handleUnfollow = () => {
    const token = localStorage.getItem("token");
    setIsRequestPending(true);
    axios
      .post(
        `http://localhost:8080/unfollow/${objectId}`,
        {},
        {
          headers: {
            Authorization: `Bearer ${token}`,
          },
        }
      )
      .then(() => {
        setFollowStatus("NONE");
        fetchFollowStatus(); // Atualiza o status de seguir após enviar a solicitação
      })
      .catch((error) => {
        setError(error.message);
      })
      .finally(() => {
        setIsRequestPending(false);
      });
  };

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
              <button
                onClick={
                  followStatus === "ACCEPTED"
                    ? handleUnfollow
                    : followStatus === "PENDING"
                    ? null
                    : handleFollow
                }
                className={`follow-button ${
                  followStatus === "ACCEPTED"
                    ? "following"
                    : followStatus === "PENDING"
                    ? "pending"
                    : ""
                }`}
                disabled={followStatus === "PENDING" || isRequestPending}
              >
                {followStatus === "ACCEPTED"
                  ? "Parar de seguir"
                  : followStatus === "PENDING" || isRequestPending
                  ? "Pendente"
                  : "Pedir para seguir"}
              </button>
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
