import React, { useState, useEffect } from "react";
import "../css/Header.css";
import { FaMoon, FaHome } from "react-icons/fa";
import { CiLight } from "react-icons/ci";
import { FaUserPlus, FaSignInAlt, FaUser, FaSignOutAlt } from "react-icons/fa";
import { Link, useNavigate } from "react-router-dom";

function Header() {
    const [theme, setTheme] = useState(false); 
    const [user, setUser] = useState(null); // Kullanıcı durumu
    const navigate = useNavigate(); 

    useEffect(() => {
        // localStorage'dan token alıyoruz
        const token = localStorage.getItem("userToken");

        if (token) {
            setUser(true); // Token varsa, kullanıcıyı giriş yapmış olarak kabul ediyoruz
        } else {
            setUser(false); 
        }
    }, []); // Sayfa ilk açıldığında bir kez çalışması için boş array ekliyoruz

    // Tema değişim fonksiyonu
    const changeTheme = () => {
        const root = document.getElementById("root");
        if (theme) {
            root.style.backgroundColor = "#fff";
            root.style.color = "black";
        } else {
            root.style.backgroundColor = "black";
            root.style.color = "#fff";
        }
        setTheme(!theme); // Tema durumunu değiştiriyoruz
    };

    const handleLogout = () => {
        localStorage.removeItem("userToken"); // Kullanıcıyı çıkış yaparken token'ı silmeliyiz
        setUser(false); // Kullanıcıyı çıkış yapmış olarak işaretliyoruz
        navigate("/"); // Kullanıcıyı ana sayfaya yönlendiriyoruz
    };

    return (
        <div className="header-container">
            {/* Logo ve Şirket Adı */}
            <Link to="/" className="logo-container">
                <img className="logo" src="./src/image/logo.png" alt="Logo" />
                <p className="logo-text">CyberAirlines</p>
            </Link>

            {/* Menü Linkleri */}
            <div className="menu-links">
                <Link to="/" className="menu-link">
                    <FaHome /> Ana Sayfa
                </Link>
                <Link to="/bilet" className="menu-link">
                    Bilet Al
                </Link>
                <Link to="/checkin" className="menu-link">
                    Check-in
                </Link>
                <Link to="/ucusnoktaları" className="menu-link">
                    Uçuş Noktaları
                </Link>

                <Link to="/Biletlerim" className="menu-link">
                    Biletlerim
                </Link>
                <Link to="/sss" className="menu-link">
                    S.S.S
                </Link>

            </div>


            {/* Sağ Taraf - Arama ve Giriş */}
            <div className="right-section">
                <input className="search-input" type="text" placeholder="Ne aramak istersiniz?" />
                {theme ? (
                    <FaMoon className="icon" onClick={changeTheme} />
                ) : (
                    <CiLight className="icon" onClick={changeTheme} />
                )}
                {user ? (
                    <>
                        <Link to="/profile" className="menu-link">
                            <FaUser /> Profil
                        </Link>
                        <button onClick={handleLogout} className="menu-link">
                            <FaSignOutAlt /> Çıkış Yap
                        </button>
                    </>
                ) : (
                    <>
                        <Link to="/login" className="menu-link">
                            <FaSignInAlt /> Giriş Yap
                        </Link>
                        <Link to="/register" className="menu-link">
                            <FaUserPlus /> Üye Ol
                        </Link>
                    </>
                )}
            </div>
        </div>
    );
}

export default Header;
