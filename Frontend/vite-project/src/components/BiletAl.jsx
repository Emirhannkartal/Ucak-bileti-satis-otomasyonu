import React, { useState, useEffect } from "react";
import axios from "axios";
import { useNavigate } from "react-router-dom";
import "../css/BiletAl.css";

function BiletAl() {
    const [airports, setAirports] = useState([]);       // Havaalanı listesini tuttuk burda
    const [selectedFrom, setSelectedFrom] = useState(""); //kalkış havaalanı burda belirledik
    const [selectedTo, setSelectedTo] = useState("");
    const [error, setError] = useState("");
    const navigate = useNavigate(); //Sayfa yönlendirmesi için navigate fonksiyonunu kullandık

    useEffect(() => {
        const token = localStorage.getItem("userToken");

        if (!token) {
            navigate("/login");  // Kullanıcı login değilse giriş sayfasına yönlendirdik
            return;
        }

        const fetchAirports = async () => {
            try {
                const response = await axios.get("http://localhost:8080/api/v1/airports", { //get istegi ile havalimanı bilgilerini çektik
                    headers: {
                        Authorization: `Bearer ${token}`,  // istek yaparken tokeni kulandık 
                    },
                });
                setAirports(response.data); // Havaalanı verilerini state'e kaydettik
            } catch (err) {
                console.error("Havaalanı bilgisi hatası:", err);
                setError("Havaalanı bilgileri yüklenirken hata oluştu.");
            }
        };

        fetchAirports();
    }, [navigate]);

    const handleSearch = () => {
        if (!selectedFrom || !selectedTo) {
            setError("Lütfen 'Nereden' ve 'Nereye' alanlarını doldurun.");
            return;
        }
        setError("");

        // Havaalanı isimlerini ucus sayfasına göndermek için encodeURIComponent kullandık bu sayede URL içinde bilgilerin bozulmaması ve doğru şekilde iletilmesini sağladık
        navigate(`/detay?from=${encodeURIComponent(selectedFrom)}&to=${encodeURIComponent(selectedTo)}`);
    };

    return (
        <div className="bilet-al-container">
            <div className="bilet-form-wrapper">
                <h2 className="bilet-form-title">Bilet Al</h2>
                {error && <div className="error">{error}</div>}
                <div className="bilet-form">
                    <div className="form-group">
                        <label>Nereden:</label>
                        <select value={selectedFrom} onChange={(e) => setSelectedFrom(e.target.value)}>
                            <option value="">Havaalanı Seçiniz</option>
                            {airports.map((airport) => (
                                <option key={airport.id} value={airport.airportName}>
                                    {airport.airportName}
                                </option>
                            ))}
                        </select>
                    </div>
                    <div className="form-group">
                        <label>Nereye:</label>
                        <select value={selectedTo} onChange={(e) => setSelectedTo(e.target.value)}>
                            <option value="">Havaalanı Seçiniz</option>
                            {airports.map((airport) => (
                                <option key={airport.id} value={airport.airportName}>
                                    {airport.airportName}
                                </option>
                            ))}
                        </select>
                    </div>
                    <button onClick={handleSearch} className="btn-submit">
                        Uçuş Ara
                    </button>
                </div>
            </div>
        </div>
    );
}

export default BiletAl;
