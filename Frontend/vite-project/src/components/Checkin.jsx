import React, { useState, useEffect } from 'react';
import axios from 'axios';
import { useNavigate } from 'react-router-dom';
import "../css/Checkin.css"; // CSS dosyanızın yolunu ekleyin

function Checkin() {
    const [pnrCode, setPnrCode] = useState('');
    const [lastName, setLastName] = useState('');
    const [message, setMessage] = useState('');
    const [error, setError] = useState('');
    const navigate = useNavigate();

    useEffect(() => {
        const token = localStorage.getItem('userToken');
        if (!token) {
            navigate("/login");  // Kullanıcı login değilse giriş sayfasına yönlendir
        }
    }, [navigate]);

    const handleCheckin = async (e) => {
        e.preventDefault();

        // Gerekli alanların kontrolünü yaptık
        if (!pnrCode || !lastName) {
            setError('Lütfen tüm alanları doldurun.');
            setMessage('');
            return;
        }

        const data = { pnrCode, lastName };

        // Token'ı localStorage'dan dık
        const token = localStorage.getItem('userToken');
        if (!token) {
            setError('Yetkilendirme hatası: Giriş yapmadınız.');
            setMessage('');
            return;
        }

        try {
            const response = await axios.post(
                'http://localhost:8080/api/v1/checkin',
                data,
                {
                    headers: {
                        Authorization: `Bearer ${token}`
                    }
                }
            );

            // API cevabını logla
            console.log('API Response Data:', response.data); // API yanıtı
            console.log('API Response Type:', typeof response.data); // Yanıtın tipi

            if (response.data) {
                const responseMessage = response.data.toString(); // Yanıtı string'e çevirdik
                console.log('API Yanıtı:', responseMessage); 

                if (responseMessage.includes('Check-in işlemi başarıyla tamamlandı')) {
                    setMessage('Check-in işlemi başarıyla tamamlandı.');
                    setError('');
                } else if (responseMessage.includes('Bilet bulunamadı')) {
                    setError('Bilet bulunamadı. Lütfen soyadınızı ve PNR kodunu kontrol edin.');
                    setMessage('');
                } else {
                    setError('Bu bilet için zaten check-in yapılmış.');
                    setMessage('');
                }
            } else {
                setError('API boş bir yanıt döndü.');
                setMessage('');
            }

        } catch (err) {
            console.error('Check-in hatası:', err);
            if (err.response) {
                console.log('Hata Yanıtı:', err.response);
                setError(`API Hatası: ${err.response.data || 'Bir hata oluştu.'}`);
            } else if (err.request) {
                console.log('İstek hatası:', err.request);
                setError('Sunucudan yanıt alınamadı.');
            } else {
                console.log('Hata:', err.message);
                setError('Bir hata oluştu: ' + err.message);
            }
        }

    };

    return (
        <div className="checkin-container">
            <h2>Check-in Yap</h2>
            <form onSubmit={handleCheckin} className="checkin-form">
                <div className="form-group">
                    <label htmlFor="pnrCode">PNR Kodu</label>
                    <input
                        type="text"
                        id="pnrCode"
                        name="pnrCode"
                        value={pnrCode}
                        onChange={(e) => setPnrCode(e.target.value)}
                        required
                    />
                </div>
                <div className="form-group">
                    <label htmlFor="lastName">Soyad</label>
                    <input
                        type="text"
                        id="lastName"
                        name="lastName"
                        value={lastName}
                        onChange={(e) => setLastName(e.target.value)}
                        required
                    />
                </div>
                <button type="submit" className="checkin-btn">Check-in Yap</button>
            </form>

            {/* Başarı ve hata mesajlarını burda gösterdik */}
            {message && <p className="success-message">{message}</p>}
            {error && <p className="error-message">{error}</p>}
        </div>
    );
}

export default Checkin;
