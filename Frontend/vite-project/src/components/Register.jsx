import React, { useState } from "react";
import axios from "axios";  
import "../css/Register.css";

function Register() {
    const [username, setUsername] = useState("");
    const [password, setPassword] = useState("");
    const [confirmPassword, setConfirmPassword] = useState("");

    const handleRegister = async (e) => {
        e.preventDefault();


        if (password !== confirmPassword) {
            alert("Şifreler eşleşmiyor!");
            return;
        }

        //  adlıgımız verileri apiye gönderdik
        try {
            const response = await axios.post("http://localhost:8080/register", {
                username: username,
                password: password
            });

            // Başarılı kayıt durumunda ekrana yazdırdık
            if (response.status === 200) {
                alert("Kayıt başarılı!");
            } else {
                alert("Kayıt sırasında bir hata oluştu.");
            }
        } catch (error) {
            console.error("Kayıt hatası:", error);
            alert("Kayıt işlemi sırasında bir hata oluştu.");
        }
    };

    return (
        <div className="register-container">
            <form className="register-form" onSubmit={handleRegister}>
                <h2 className="register-title">Kayıt Ol</h2>
                <div className="form-group">
                    <label htmlFor="username">Kullanıcı Adı</label>
                    <input
                        type="text"
                        id="username"
                        placeholder="Kullanıcı adınızı girin"
                        value={username}
                        onChange={(e) => setUsername(e.target.value)}
                        required
                    />
                </div>
                <div className="form-group">
                    <label htmlFor="password">Şifre</label>
                    <input
                        type="password"
                        id="password"
                        placeholder="Şifrenizi girin"
                        value={password}
                        onChange={(e) => setPassword(e.target.value)}
                        required
                    />
                </div>
                <div className="form-group">
                    <label htmlFor="confirm-password">Şifreyi Doğrula</label>
                    <input
                        type="password"
                        id="confirm-password"
                        placeholder="Şifrenizi tekrar girin"
                        value={confirmPassword}
                        onChange={(e) => setConfirmPassword(e.target.value)}
                        required
                    />
                </div>
                <button type="submit" className="register-button">
                    Kayıt Ol
                </button>
            </form>
        </div>
    );
}

export default Register;
