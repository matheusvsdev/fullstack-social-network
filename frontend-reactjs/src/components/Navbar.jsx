import "./Navbar.css";
import logo from "../assets/logo.png";

// Components
import { NavLink, Link } from "react-router-dom";
import {
  BsSearch,
  BsHouseDoorFill,
  BsFillPersonFill,
  BsFillCameraFill,
} from "react-icons/bs";

// Hooks
import { useState, useEffect } from "react";

const Navbar = () => {
  const [isLoggedIn, setIsLoggedIn] = useState(false);

  useEffect(() => {
    const token = localStorage.getItem("token");
    if (token) {
      setIsLoggedIn(true);
    }
  }, []);

  const handleLogout = () => {
    localStorage.removeItem("token");
    setIsLoggedIn(false);
    window.location.href = "/register";
  };

  if (!isLoggedIn) {
    return null;
  }

  return (
    <nav id="nav">
      <Link to="/">
        <img src={logo} alt="Logo" id="logo" />
      </Link>
      <form id="search-form">
        <BsSearch />
        <input type="text" placeholder="Pesquisar" />
      </form>
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
          <span onClick={handleLogout}>Sair</span>
        </li>
      </ul>
    </nav>
  );
};

export default Navbar;
