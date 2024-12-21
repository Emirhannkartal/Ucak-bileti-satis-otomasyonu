import React, { useState, useEffect } from "react";
import axios from "axios";
import { useNavigate } from "react-router-dom";
import "../css/Profile.css";

function Profile() {
    const [firstName, setFirstName] = useState("");
    const [lastName, setLastName] = useState("");
    const [email, setEmail] = useState("");
    const [birthOfDate, setBirthOfDate] = useState("");
    const [error, setError] = useState("");
    const [successMessage, setSuccessMessage] = useState("");
    const [userInfo, setUserInfo] = useState(null);
    const navigate = useNavigate();

    // Sayfa yüklendiğinde kullanıcı verilerini getirmek için burayı kullandık
    useEffect(() => {
        const fetchUserInfo = async () => {
            const token = localStorage.getItem("userToken");
            const passengerId = localStorage.getItem("passengerId");

            if (!token || !passengerId) {
                setError("Giriş yapmanız gerekiyor.");
                navigate("/login");
                return;
            }

            try {
                // Kullanıcı bilgilerini getirme apisi
                const response = await axios.get(`http://localhost:8080/api/v1/passengers/${passengerId}`, {
                    headers: { Authorization: `Bearer ${token}` },
                });

                if (response.data) {
                    const { firstName, lastName, email, birthOfDate, id } = response.data;
                    setUserInfo({ firstName, lastName, email, birthOfDate, passengerId: id });
                    setFirstName(firstName);
                    setLastName(lastName);
                    setEmail(email);
                    setBirthOfDate(birthOfDate);
                    localStorage.setItem("passengerId", id);
                }
            } catch (error) {
                setError("Kullanıcı bilgileri alınamadı!");
                console.error(error);
            }
        };

        fetchUserInfo();
    }, [navigate]);

    // Profil güncelledik burda
    const handleSubmit = async (e) => {
        e.preventDefault();
        const token = localStorage.getItem("userToken");
        const passengerId = localStorage.getItem("passengerId");

        if (!token || !passengerId) {
            setError("Token bulunamadı, giriş yapmanız gerekiyor.");
            return;
        }

        const updatedUser = { email, firstName, lastName, birthOfDate };

        try {
            // Kullanıcıyı güncelleledik
            const response = await axios.put(
                `http://localhost:8080/api/v1/passengers/${passengerId}`,
                updatedUser,
                {
                    headers: { Authorization: `Bearer ${token}` },
                }
            );

            if (response.status === 200) {
                setUserInfo(response.data);
                setSuccessMessage("Profil başarıyla güncellendi!");
                setError("");

                // Güncel bilgileri Local Storage'a kaydettik
                localStorage.setItem("passengerId", response.data.id);
            }
        } catch (error) {
            setError("Profil güncellenirken hata oluştu!");
            console.error(error);
        }
    };

    return (
        <div className="profile-container">
            <h2>Profil</h2>
            {error && <div className="error-message">{error}</div>}
            {successMessage && <div className="success-message">{successMessage}</div>}

            {userInfo ? (
                <div>
                    <h3>Hoşgeldiniz, {userInfo.firstName} {userInfo.lastName}</h3>
                    <form onSubmit={handleSubmit}>
                        <div>
                            <label htmlFor="firstName">Ad</label>
                            <input
                                type="text"
                                id="firstName"
                                value={firstName}
                                onChange={(e) => setFirstName(e.target.value)}
                                required
                            />
                        </div>
                        <div>
                            <label htmlFor="lastName">Soyad</label>
                            <input
                                type="text"
                                id="lastName"
                                value={lastName}
                                onChange={(e) => setLastName(e.target.value)}
                                required
                            />
                        </div>
                        <div>
                            <label htmlFor="birthOfDate">Doğum Tarihi</label>
                            <input
                                type="date"
                                id="birthOfDate"
                                value={birthOfDate}
                                onChange={(e) => setBirthOfDate(e.target.value)}
                                required
                            />
                        </div>
                        <div>
                            <label htmlFor="email">E-posta</label>
                            <input
                                type="email"
                                id="email"
                                value={email}
                                onChange={(e) => setEmail(e.target.value)}
                                required
                            />
                        </div>
                        <button type="submit">Profili Güncelle</button>
                    </form>
                </div>
            ) : (
                <p>Bilgiler yükleniyor...</p>
            )}
        </div>
    );
}

export default Profile;
