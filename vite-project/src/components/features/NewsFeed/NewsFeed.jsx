import "./NewsFeed.css";
import { useState, useEffect } from "react";
import axios from "axios";

const NewsFeed = () => {
  const [newsItems, setNewsItems] = useState([]);

  const JWT_TOKEN = localStorage.getItem("userToken");

  useEffect(() => {
    const fetchNews = async () => {
      try {
        const response = await axios.get("http://localhost:3333/newsIBGE/7", {
          headers: {
            Authorization: `Bearer ${JWT_TOKEN}`,
          },
        });
        setNewsItems(response.data.newsList);
      } catch (error) {
        console.error("Erro ao buscar notícias:", error);
      }
    };
    fetchNews();
  }, []);

  return (
    <div className="news-feed">
      <div className="main-news-item">
        {newsItems.length > 0 && (
          <>
            <h1 className="main-news-subtitle">
              <a href={newsItems[0].link}>{newsItems[0].titulo}</a>
            </h1>
            <a href={newsItems[0].link}>
              <img
                src={newsItems[0].imagens}
                alt={newsItems[0].titulo}
                className="secondary-news-image"
              />
            </a>
            <p className="main-news-summary">
              <a href={newsItems[0].link}>{newsItems[0].introducao}</a>{" "}
            </p>
          </>
        )}
      </div>

      <div className="secondary-news-container">
        {newsItems.slice(1, 4).map((news) => (
          <div key={news.id} className="secondary-news-item">
            <a href={news.link}>
              {news.imagens && (
                <img
                  src={news.imagens}
                  alt={news.titulo}
                  className="secondary-news-image"
                />
              )}
            </a>
            <div className="secondary-news-text">
              <h2 className="secondary-news-title">
                <a href={news.link}>{news.editorias}</a>{" "}
              </h2>
              <h3 className="secondary-news-subtitle">
                <a href={news.link}>{news.titulo}</a>{" "}
              </h3>
              <p className="secondary-news-summary">
                <a href={news.link}>{news.introducao}</a>{" "}
              </p>
            </div>
          </div>
        ))}
      </div>

      <div className="additional-news-container">
        {newsItems.slice(4).map((news) => (
          <div key={news.id} className="additional-news-item">
            <div className="additional-news-text">
              <h2 className="additional-news-title">
                <a href={news.link}>{news.editorias}</a>
              </h2>
              <h3 className="additional-news-subtitle">
                <a href={news.link}>{news.titulo}</a>
              </h3>
              <p className="additional-news-summary">
                <a href={news.link}>{news.introducao}</a>
              </p>
            </div>
          </div>
        ))}
      </div>
    </div>
  );
};

export default NewsFeed;
