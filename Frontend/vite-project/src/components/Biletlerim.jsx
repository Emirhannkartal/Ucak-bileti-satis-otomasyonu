import React, { useState, useEffect } from "react";
import "../css/Biletlerim.css";
import Footer from "../components/Footer";
import { useNavigate } from "react-router-dom";
import axios from "axios";

function Biletlerim() {
    const [tickets, setTickets] = useState([]);  // Biletleri tutan state
    const [error, setError] = useState("");      // Hata mesajı için state
    const [loading, setLoading] = useState(true); // Yüklenme durumu
    const navigate = useNavigate();

    useEffect(() => {
        const token = localStorage.getItem("userToken");

        // Token kontrolü, giriş yapılmamışsa login sayfasına yönlendir
        if (!token) {
            navigate("/login");
            return;
        }

        const fetchTickets = async () => {
            try {
                const response = await axios.get("http://localhost:8080/api/v1/tickets", {
                    headers: {
                        Authorization: `Bearer ${token}` // Token'ı header'a ekle
                    }
                });
                setTickets(response.data); // API'den gelen biletleri state'e kaydet
            } catch (err) {
                console.error("Biletleri çekerken hata oluştu:", err);
                setError("Biletler yüklenirken bir hata oluştu.");
            } finally {
                setLoading(false); // Yüklenme tamamlandı
            }
        };

        fetchTickets();
    }, [navigate]);

    return (
        <div className="biletlerim-container">
            <div className="ticket-header">
                <h2>Biletlerim</h2>
            </div>
            <div className="ticket-content">
                {loading ? (
                    <p className="loading-message">Biletler yükleniyor...</p>
                ) : error ? (
                    <p className="error-message">{error}</p>
                ) : tickets.length === 0 ? (
                    <p className="no-ticket">Henüz biletiniz yok.</p>
                ) : (
                    <div className="ticket-cards">
                        {tickets.map((ticket, index) => (
                            <div className="ticket-card" key={index}>
                                <h3>Uçuş Bilgileri</h3>
                                <div className="ticket-info">
                                    <div className="ticket-detail">
                                        <strong>Koltuk Numarası:</strong>
                                        <p>{ticket.seatNumber}</p>
                                    </div>
                                    <div className="ticket-detail">
                                        <strong>PNR Kodu:</strong>
                                        <p>{ticket.pnrCode}</p>
                                    </div>
                                    <div className="ticket-detail">
                                        <strong>Yolcu ID:</strong>
                                        <p>{ticket.passengerId}</p>
                                    </div>
                                    <div className="ticket-detail">
                                        <strong>Uçuş ID:</strong>
                                        <p>{ticket.flightId}</p>
                                    </div>
                                </div>
                            </div>
                        ))}
                    </div>
                )}
            </div>

        </div>
    );
}

export default Biletlerim;
