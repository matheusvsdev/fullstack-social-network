import "./Navbar.css";
import logo from "../assets/logo_social.png";
// Components
import { NavLink, Link } from "react-router-dom";
import {
  BsSearch,
  BsHouseDoorFill,
  BsFillPersonFill,
  BsFillCameraFill,
  BsBellFill,
} from "react-icons/bs";
// Hooks
import { useState, useEffect } from "react";
import axios from "axios";

const Navbar = () => {
  const [isLoggedIn, setIsLoggedIn] = useState(false);
  const [searchTerm, setSearchTerm] = useState("");
  const [searchResults, setSearchResults] = useState([]);
  const [error, setError] = useState(null);
  const [showResults, setShowResults] = useState(false);
  const [notifications, setNotifications] = useState([]);
  const [showNotifications, setShowNotifications] = useState(false);
  const [loading, setLoading] = useState(false);

  useEffect(() => {
    const token = localStorage.getItem("token");
    if (token) {
      setIsLoggedIn(true);
      loadNotifications(token);
    }
  }, []);

  const loadNotifications = (token) => {
    axios
      .get("http://localhost:8080/follow/pending-requests", {
        headers: {
          Authorization: `Bearer ${token}`,
        },
      })
      .then((response) => {
        // Filtrar notificações pendentes
        const pendingNotifications = response.data.filter(
          (notif) => notif.state === "PENDING"
        );
        setNotifications(pendingNotifications);
      })
      .catch((error) => {
        setError(error.message);
      });
  };

  const handleLogout = () => {
    localStorage.removeItem("token");
    setIsLoggedIn(false);
    window.location.href = "/register";
  };

  const handleSearch = () => {
    const token = localStorage.getItem("token");
    setLoading(true);

    axios
      .get("http://localhost:8080/profiles/search", {
        params: { username: searchTerm },
        headers: {
          Authorization: `Bearer ${token}`,
        },
      })
      .then((response) => {
        setSearchResults(response.data.content);
        setShowResults(true);
        setLoading(false);
      })
      .catch((error) => {
        setError(error.message);
        setLoading(false);
      });
  };

  const handleToggleSearch = () => {
    if (showResults) {
      setShowResults(false);
    } else {
      handleSearch();
    }
  };

  const handleToggleNotifications = () => {
    setShowNotifications(!showNotifications);
  };

  const handleAcceptFollowRequest = (requestId) => {
    const token = localStorage.getItem("token");
    axios
      .post(
        `http://localhost:8080/follow/accept/${requestId}`,
        {},
        {
          headers: {
            Authorization: `Bearer ${token}`,
          },
        }
      )
      .then(() => {
        // Remover a notificação aceita
        setNotifications((prev) =>
          prev.filter((notif) => notif.objectId !== requestId)
        );
      })
      .catch((error) => {
        setError(error.message);
      });
  };

  const handleRejectFollowRequest = (requestId) => {
    const token = localStorage.getItem("token");
    axios
      .post(
        `http://localhost:8080/follow/reject/${requestId}`,
        {},
        {
          headers: {
            Authorization: `Bearer ${token}`,
          },
        }
      )
      .then(() => {
        // Remover a notificação rejeitada
        setNotifications((prev) =>
          prev.filter((notif) => notif.objectId !== requestId)
        );
      })
      .catch((error) => {
        setError(error.message);
      });
  };

  if (!isLoggedIn) {
    return null;
  }

  return (
    <nav id="nav">
      <Link to="/">
        <img src={logo} alt="Logo" id="logo" />
      </Link>
      <form id="search-form" onSubmit={(e) => e.preventDefault()}>
        <BsSearch onClick={handleToggleSearch} style={{ cursor: "pointer" }} />
        <input
          type="text"
          placeholder="Pesquisar"
          value={searchTerm}
          onChange={(e) => setSearchTerm(e.target.value)}
          onKeyUp={(e) => {
            if (e.key === "Enter") handleSearch();
          }}
        />
        {loading && <p>Carregando...</p>}
        {showResults && searchResults.length > 0 && (
          <div className="search-results">
            {searchResults.map((user) => (
              <div key={user.objectId} className="user-result">
                <Link to={`/profiles/${user.objectId}`}>
                  <img
                    src={user.profileImage}
                    alt="Profile"
                    style={{
                      width: "50px",
                      height: "50px",
                      borderRadius: "50%",
                    }}
                  />
                  <p>{user.username}</p>
                </Link>
              </div>
            ))}
          </div>
        )}
      </form>
      {error && <p style={{ color: "red" }}>{error}</p>}
      <ul id="nav-links">
        <li>
          <NavLink to="/">
            <BsHouseDoorFill />
          </NavLink>
        </li>
        <li>
          <NavLink to="/camera">
            <BsFillCameraFill />
          </NavLink>
        </li>
        <li>
          <NavLink to="/profile">
            <BsFillPersonFill />
          </NavLink>
        </li>
        <li>
          <BsBellFill
            onClick={handleToggleNotifications}
            style={{ cursor: "pointer" }}
          />
          {showNotifications && (
            <div className="notifications">
              {notifications.length > 0 ? (
                notifications.map((notif) => (
                  <div key={notif.objectId} className="notification">
                    <p>{notif.username}</p>
                    <button
                      onClick={() => handleAcceptFollowRequest(notif.objectId)}
                    >
                      Aceitar
                    </button>
                    <button
                      onClick={() => handleRejectFollowRequest(notif.objectId)}
                    >
                      Rejeitar
                    </button>
                  </div>
                ))
              ) : (
                <p>Sem notificações pendentes</p>
              )}
            </div>
          )}
        </li>
        <li>
          <span onClick={handleLogout}>Sair</span>
        </li>
      </ul>
    </nav>
  );
};

export default Navbar;
