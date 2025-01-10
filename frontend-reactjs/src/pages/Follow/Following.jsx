import { useState, useEffect } from "react";
import axios from "axios";
import { useParams } from "react-router-dom";
import "./Follow.css";

const Following = () => {
  const { objectId } = useParams();
  const [followings, setFollowing] = useState([]);
  const [error, setError] = useState(null);

  useEffect(() => {
    const token = localStorage.getItem("token");
    axios
      .get(`http://localhost:8080/following`, {
        headers: {
          Authorization: `Bearer ${token}`,
        },
      })
      .then((response) => {
        setFollowing(response.data);
      })
      .catch((error) => {
        setError(error.message);
      });
  }, [objectId]);

  return (
    <div className="follow-container">
      <h2>Seguindo</h2>
      {error && <p style={{ color: "red" }}>{error}</p>}
      <ul>
        {followings.map((following) => (
          <li key={following.id}>
            <img
              src={
                following.imageProfile ||
                "https://www.nicepng.com/png/detail/128-1280406_view-user-icon-png-user-circle-icon-png.png"
              }
              alt="Profile"
              className="follow-image"
            />
            <p>{following.username}</p>
          </li>
        ))}
      </ul>
    </div>
  );
};

export default Following;
