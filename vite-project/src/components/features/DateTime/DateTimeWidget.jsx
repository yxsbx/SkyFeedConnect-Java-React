import { useState, useEffect } from "react";
import "./DateTimeWidget.css";

const DateTimeWidget = () => {
  const [currentDateTime, setCurrentDateTime] = useState(new Date());

  useEffect(() => {
    const intervalId = setInterval(() => {
      setCurrentDateTime(new Date());
    }, 1000);

    return () => clearInterval(intervalId);
  }, []);

  const formatDate = (date) => {
    const options = {
      weekday: "long",
      year: "numeric",
      month: "long",
      day: "numeric",
    };
    return date.toLocaleDateString("pt-BR", options);
  };

  const formatTime = (date) => {
    const options = {
      hour: "numeric",
      minute: "numeric",
      second: "numeric",
      hour12: false,
    };
    return date.toLocaleTimeString("pt-BR", options);
  };

  return (
    <div id="datetime-widget">
      <div id="datetime-content">
        <section id="date-time-info">
          <div className="datetime-data">
            <h2>{formatTime(currentDateTime)}</h2>
            <h3>{formatDate(currentDateTime)}</h3>
          </div>
        </section>
      </div>
    </div>
  );
};

export default DateTimeWidget;
