import React, { useState } from "react";
import { useNavigate } from "react-router-dom"; 
import axios from "axios"; 
import "../css/Login.css"; 

function Login() {
    const [username, setUsername] = useState("");
    const [password, setPassword] = useState("");
    const [error, setError] = useState(""); 
    const navigate = useNavigate(); // Yönlendirme için useNavigate kullanıyoruz

    const handleLogin = async (e) => {
        e.preventDefault();

        try {
            const response = await axios.post("http://localhost:8080/authenticate", {
                username,
                password,
            });

            console.log("Backend yanıtı:", response); // Yanıtı kontrol etmek için yazdırıyoruz.

            
            if (response.data.payload && response.data.payload.accesstoken) {
                localStorage.setItem("userToken", response.data.payload.accesstoken);
                console.log("Alınan Token:", response.data.payload.accesstoken); 
                navigate("/"); // Ana sayfaya yönlendir giriş başarıli ise
                window.location.reload();
            } else {
                setError("Geçersiz giriş! Lütfen tekrar deneyin.");
                console.error("Geçersiz giriş - Token alınamadı.");
            }
        } catch (error) {
            if (error.response) {
                const errorMessage = error.response.data.message || "Giriş işlemi başarısız!";
                setError(errorMessage);
                console.error("Backend Hatası:", error.response.data); 
            } else {
                setError("Giriş işlemi başarısız! Lütfen tekrar deneyin.");
                console.error("Giriş hatası:", error.message); 
            }
        }
    };


    return (
        <div className="login-container">
            <div className="login-form">
                <h2 className="login-title">Giriş Yap</h2>
                <form onSubmit={handleLogin}>
                    <div className="form-group">
                        <label htmlFor="username">Kullanıcı Adı</label>
                        <input
                            type="text"
                            id="username"
                            name="username"
                            value={username}
                            onChange={(e) => setUsername(e.target.value)}
                            placeholder="Kullanıcı adınızı girin"
                            required
                        />
                    </div>
                    <div className="form-group">
                        <label htmlFor="password">Şifre</label>
                        <input
                            type="password"
                            id="password"
                            name="password"
                            value={password}
                            onChange={(e) => setPassword(e.target.value)}
                            placeholder="Şifrenizi girin"
                            required
                        />
                    </div>
                    {error && <div className="error-message">{error}</div>}
                    <button type="submit" className="login-button">Giriş Yap</button>
                </form>
            </div>
        </div>
    );
}

export default Login;
