import PropTypes from "prop-types";
import { Link } from "react-router-dom";
import "./MainPage.css";
import WeatherWidget from "@features/Weather/WeatherSearch.jsx";
import NewsFeed from "@features/NewsFeed/NewsFeed.jsx";
import logo from "@images/logo2.png";
import DateTimeWidget from "@features/DateTime/DateTimeWidget.jsx";
import { RxExit } from "react-icons/rx";

const MainPage = ({ onLogout }) => {
  const username = localStorage.getItem("userName") || "Usuário";

  return (
    <div id="main-content" className="container">
      <header className="header">
        <div className="header-content">
          <div className="brand">
            <img src={logo} alt="Logo" className="logo" />
            <div className="header-title">
              <h1>SKY FEED CONNECT</h1>
              <p>Porque sua vida conosco é mais conectada</p>
            </div>
          </div>
          <div className="header-user-info">
            <span className="username">{username}</span>
            <Link to="/login" onClick={onLogout} className="logout-link">
              <RxExit />
            </Link>
          </div>
        </div>
      </header>
      <main id="principal">
        <NewsFeed className="news-feed" />
        <aside className="time-aside">
          <WeatherWidget />
          <DateTimeWidget />
        </aside>
      </main>
    </div>
  );
};

MainPage.propTypes = {
  onLogout: PropTypes.func.isRequired,
};

export default MainPage;
