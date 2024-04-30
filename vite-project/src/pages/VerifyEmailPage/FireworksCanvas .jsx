import { useEffect, useRef, useState } from "react";
import styles from "./FireworksCanvas.module.css";

const particleCount = 150;
const colors = ["pink", "white"];
let particles = [];

const FireworksCanvas = () => {
  const canvasRef = useRef(null);
  const [canvasSize, setCanvasSize] = useState({ width: 0, height: 0 });

  useEffect(() => {
    const canvas = canvasRef.current;
    const ctx = canvas.getContext("2d");
    canvas.width = canvasSize.width;
    canvas.height = canvasSize.height;

    class Particle {
      constructor(x, y, color) {
        this.x = x;
        this.y = y;
        this.color = color;
        this.radius = Math.random() * 2 + 1;
        this.velocity = {
          x: Math.random() * 2 - 1,
          y: Math.random() * 2 - 1,
        };
        this.alpha = 1;
      }

      draw() {
        ctx.beginPath();
        ctx.arc(this.x, this.y, this.radius, 0, Math.PI * 2);
        ctx.fillStyle = this.color;
        ctx.closePath();
        ctx.fill();
      }

      update() {
        this.x += this.velocity.x;
        this.y += this.velocity.y;
        this.alpha -= 0.01;
        this.radius -= 0.01;
        this.draw();
      }
    }

    function createFirework(x, y) {
      for (let i = 0; i < particleCount; i++) {
        const color = colors[Math.floor(Math.random() * colors.length)];
        const particle = new Particle(x, y, color);
        particles.push(particle);
      }
    }

    function animate() {
      ctx.clearRect(0, 0, canvas.width, canvas.height);

      for (let i = 0; i < particles.length; i++) {
        particles[i].update();

        if (particles[i].alpha <= 0 || particles[i].radius <= 0) {
          particles.splice(i, 1);
          i--;
        }
      }
      setTimeout(() => {
        requestAnimationFrame(animate);
      }, 5);
    }

    const handleClick = (event) => {
      const mouseX = event.clientX;
      const mouseY = event.clientY;
      createFirework(mouseX, mouseY);
    };

    canvas.addEventListener("click", handleClick);
    animate();

    return () => {
      canvas.removeEventListener("click", handleClick);
    };
  }, [canvasSize]);

  useEffect(() => {
    const handleResize = () => {
      setCanvasSize({
        width: window.innerWidth,
        height: window.innerHeight,
      });
    };

    handleResize();

    window.addEventListener("resize", handleResize);

    return () => window.removeEventListener("resize", handleResize);
  }, []);

  return <canvas ref={canvasRef} className={styles.canvas} />;
};

export default FireworksCanvas;
