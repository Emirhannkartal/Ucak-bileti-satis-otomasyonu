import React, { useEffect, useState } from "react";
import { useLocation } from "react-router-dom";
import axios from "axios";
import "../css/BiletOlusturma.css";

function BiletOlusturma() {
    const location = useLocation();
    const [ticket, setTicket] = useState(null);
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState("");

    const queryParams = new URLSearchParams(location.search);
    const passengerId = queryParams.get("passengerId");
    const flightId = queryParams.get("flightId");

    useEffect(() => {
        const createTicket = async () => {
            setLoading(true);

            const token = localStorage.getItem("userToken");
            if (!token) {
                setError("Yetkilendirme hatası: Giriş yapmadınız.");
                setLoading(false);
                return;
            }

            try {
                // API'den bilet oluşturma isteği
                const response = await axios.post(
                    "http://localhost:8080/api/v1/tickets",
                    { passengerId: parseInt(passengerId, 10), flightId: parseInt(flightId, 10) },
                    { headers: { Authorization: `Bearer ${token}` } }
                );

                console.log("API'den bilet oluşturuldu:", response.data);
                setTicket(response.data);

                // Yeni bileti 'Biletlerim' sayfasında göstermek için kaydet
                let tickets = JSON.parse(localStorage.getItem("tickets")) || [];
                tickets.push(response.data);
                localStorage.setItem("tickets", JSON.stringify(tickets));
            } catch (err) {
                console.error("Bilet oluşturma hatası:", err.response?.data || err.message);
                setError(err.response?.data?.message || "Bilet oluşturulurken bir hata oluştu.");
            } finally {
                setLoading(false);
            }
        };

        // passengerId ve flightId kontrolü
        if (passengerId && flightId) {
            createTicket();
        } else {
            setError("Yolcu ID ve Uçuş ID bilgileri eksik.");
            setLoading(false);
        }
    }, [passengerId, flightId]);

    return (
        <div className="container">
            <h2 className="page-title">Bilet Bilgileri</h2>
            {loading ? (
                <p className="loading-message">Bilet oluşturuluyor...</p>
            ) : error ? (
                <p className="error-message">{error}</p>
            ) : ticket ? (
                <div className="ticket-details">
                    <p><strong>Koltuk Numarası:</strong> {ticket.seatNumber}</p>
                    <p><strong>PNR Kodu:</strong> {ticket.pnrCode}</p>
                    <p><strong>Yolcu ID:</strong> {ticket.passengerId}</p>
                    <p><strong>Uçuş ID:</strong> {ticket.flightId}</p>
                </div>
            ) : (
                <p className="no-ticket">Bilet bilgisi alınamadı.</p>
            )}
        </div>
    );
}

export default BiletOlusturma;
