import React, { useState, useEffect } from "react";
import axios from "axios";
import "../css/UcusNoktaları.css"; 

const UcusNoktaları = () => {
    const [airports, setAirports] = useState([]); // Uçuş noktalarını tutacak state
    const [loading, setLoading] = useState(true); // Yükleme durumu
    const [error, setError] = useState(null); // Hata durumu

    useEffect(() => {
        const fetchAirports = async () => {
            try {
                // Kullanıcının token'ını localStorage'dan alıyoruz
                const token = localStorage.getItem("userToken");

                if (!token) {
                    setError("Kullanıcı giriş yapmamış.");
                    setLoading(false); // Yükleme durumunu sonlandırıyoruz
                    return;
                }

                // API'ye GET isteği atıyoruz
                const response = await axios.get("http://localhost:8080/api/v1/airports", {
                    headers: {
                        Authorization: `Bearer ${token}`, // Bearer token'ı header'a ekliyoruz
                    },
                });

                // Gelen uçuş noktalarını state'e set ediyoruz
                setAirports(response.data);
            } catch (err) {
                setError("Veriler alınırken bir hata oluştu.");
                console.error(err);
            } finally {
                setLoading(false);
            }
        };

        fetchAirports();
    }, []);

    return (
        <div className="ucus-noktalar-container">
            <h2>Uçuş Noktaları</h2>

            {loading && <p>Yükleniyor...</p>}
            {error && <p>{error}</p>}

            {airports.length === 0 && !loading && !error && <p>Hiç uçuş noktası bulunamadı.</p>}

            <ul>
                {airports.map((airport) => (
                    <li key={airport.id} className="airport-item">
                        <p><strong>Havalimanı Adı:</strong> {airport.airportName}</p>
                        <p><strong>Şehir:</strong> {airport.airportCity}</p>
                        <p><strong>Ülke:</strong> {airport.airportCountry}</p>
                    </li>
                ))}
            </ul>
        </div>
    );
};

export default UcusNoktaları;
