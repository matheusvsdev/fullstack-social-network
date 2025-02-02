import "./App.css";
import { BrowserRouter, Routes, Route } from "react-router-dom";

// Pages
import Home from "./pages/Home/Home";
import Login from "./pages/Auth/Login";
import Register from "./pages/Auth/Register";
import Profile from "./pages/Profile/Profile";
import Followers from "./pages/Follow/Followers";
import Following from "./pages/Follow/Following";
import UserProfile from "./pages/Profile/UsersProfile";
import EditProfile from "./pages/EditProfile/EditProfile";

// Components
import Navbar from "./components/Navbar";
import Footer from "./components/Footer";

function App() {
  return (
    <>
      <div className="App">
        <BrowserRouter>
          <Navbar />
          <div className="container">
            <Routes>
              <Route path="/" element={<Home />} />
              <Route path="/register" element={<Register />} />
              <Route path="/login" element={<Login />} />
              <Route path="/profile" element={<Profile />} />
              <Route path="/followers" element={<Followers />} />
              <Route path="/following" element={<Following />} />
              <Route
                path="/profiles/:objectId"
                element={<UserProfile />}
              />{" "}
              {/* Outras rotas */}
              <Route path="/edit-profile" element={<EditProfile />} />
            </Routes>
          </div>
          <Footer />
        </BrowserRouter>
      </div>
    </>
  );
}

export default App;
