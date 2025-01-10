import { useState, useEffect } from "react";
import axios from "axios";
import { useParams } from "react-router-dom";
import "./Follow.css";

const Followers = () => {
  const { objectId } = useParams();
  const [followers, setFollowers] = useState([]);
  const [error, setError] = useState(null);

  useEffect(() => {
    const token = localStorage.getItem("token");
    axios
      .get(`http://localhost:8080/followers`, {
        headers: {
          Authorization: `Bearer ${token}`,
        },
      })
      .then((response) => {
        setFollowers(response.data);
      })
      .catch((error) => {
        setError(error.message);
      });
  }, [objectId]);

  return (
    <div className="follow-container">
      <h2>Seguidores</h2>
      {error && <p style={{ color: "red" }}>{error}</p>}
      <ul>
        {followers.map((follower) => (
          <li key={follower.id}>
            <img
              src={
                follower.imageProfile ||
                "https://www.nicepng.com/png/detail/128-1280406_view-user-icon-png-user-circle-icon-png.png"
              }
              alt="Profile"
              className="follow-image"
            />
            <p>{follower.username}</p>
          </li>
        ))}
      </ul>
    </div>
  );
};

export default Followers;
