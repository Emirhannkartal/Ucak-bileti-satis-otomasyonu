import React from "react";
import { BrowserRouter as Router, Routes, Route } from "react-router-dom";// React Router kullanılarak sayfa yönlendirmeleri yaptık
import './App.css';
//projede olan sayfaları yönlendirme için import ettik 
import Header from './components/Header';
import Footer from './components/Footer';
import Home from './components/Home';
import Register from './components/Register';
import Login from './components/login';
import Profile from './components/Profile';
import BiletAl from './components/BiletAl';
import BiletAlDetay from './components/BiletAlDetay';
import BiletOlusturma from './components/BiletOlusturma';
import Biletlerim from './components/Biletlerim';
import Checkin from './components/Checkin';
import Ucusnoktaları from './components/Ucusnoktaları';

function App() {
  return (
    <Router>
      <Header />{/* Tüm sayfalarda Header olması için ana dosyaya ekledik */}
      <main> {/* Ana içeriği burada konumlandırdık */}
        <Routes>{/* Sayfa yönlendirmelerini burda yaptık bu sayfada url'e göre render işlemi oldu  */}
          <Route path="/" element={<Home />} />
          <Route path="/register" element={<Register />} />
          <Route path="/login" element={<Login />} />
          <Route path="/profile" element={<Profile />} />
          <Route path="/bilet" element={<BiletAl />} />
          <Route path="/detay" element={<BiletAlDetay />} />
          <Route path="/bilet-olusturma" element={<BiletOlusturma />} />
          <Route path="/biletlerim" element={<Biletlerim />} />
          <Route path="/checkin" element={<Checkin />} />
          <Route path="/ucusnoktaları" element={<Ucusnoktaları />} />
        </Routes>
      </main>
      <Footer />{/* Tüm sayfalarda Footer olması için ana dosyaya ekledik */}
    </Router>
  );
}

export default App;
