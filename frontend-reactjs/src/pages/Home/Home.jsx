import "./Home.css";
import { useState, useEffect } from "react";
import axios from "axios";
import { Link, useNavigate } from "react-router-dom";

const Home = () => {
  const [posts, setPosts] = useState([]);
  const [error, setError] = useState(null);
  const [showComments, setShowComments] = useState({});
  const navigate = useNavigate(); // Adicione esta linha

  useEffect(() => {
    const token = localStorage.getItem("token");

    if (!token) {
      // Se o token não estiver presente, redirecione para a página de registro
      navigate("/register");
      return;
    }

    axios
      .get("http://localhost:8080/post/following", {
        headers: {
          Authorization: `Bearer ${token}`,
        },
      })
      .then((response) => {
        const postsData = response.data;
        console.log(postsData); // Verificando dados retornados da API
        setPosts(
          postsData.map((post) => ({
            ...post,
            comments: post.comments || [], // Certifique-se de que comments esteja sempre definido
            author: {
              id: post.author?.objectId || "", // Adicione o ID do autor
              imageProfile: post.author?.imageProfile || "", // Corrigindo a chave para 'imageProfile'
              username: post.author?.username || "",
            }, // Certifique-se de que author esteja sempre definido corretamente
          }))
        );
      })
      .catch((error) => {
        setError(error.message);
      });
  }, [navigate]);

  const toggleComments = (postId) => {
    setShowComments((prev) => ({
      ...prev,
      [postId]: !prev[postId],
    }));
  };

  return (
    <div className="feed-container">
      <h2>Posts</h2>
      {error ? (
        <p style={{ color: "red" }}>{error}</p>
      ) : (
        <div className="posts">
          {posts.map((post) => (
            <div key={post.id} className="post">
              <div className="post-header">
                <img
                  src={
                    post.author.imageProfile ||
                    "https://www.nicepng.com/png/detail/128-1280406_view-user-icon-png-user-circle-icon-png.png"
                  }
                  alt="Profile"
                  className="profile-image"
                />
                <Link
                  to={`/profiles/${post.author.id}`}
                  className="username-link"
                >
                  <p className="username">{post.author.username}</p>
                </Link>
              </div>
              <img
                src={post.imageUrl}
                alt="Post Image"
                className="post-image"
              />
              <p className="post-caption">{post.caption}</p>
              <p
                className="comments-toggle"
                onClick={() => toggleComments(post.id)}
              >
                {showComments[post.id]
                  ? "Ocultar comentários"
                  : "Ver comentários"}
              </p>
              <div
                className={`comments-list ${
                  showComments[post.id] ? "active" : ""
                }`}
              >
                {post.comments.map((comment) => (
                  <div key={comment.id} className="comment-container">
                    <div className="comment">
                      <p className="comment-detail">
                        <span className="comment-author">{comment.author}</span>
                        : <span className="comment-text">{comment.text}</span>
                        <span className="comment-time">
                          {new Date(comment.createdAt).toLocaleString()}
                        </span>
                      </p>
                    </div>
                  </div>
                ))}
              </div>
            </div>
          ))}
        </div>
      )}
    </div>
  );
};

export default Home;
