import React from "react";
import "../css/Footer.css";
import { FaYoutube, FaFacebookF, FaLinkedinIn, FaInstagram, FaTwitter } from "react-icons/fa";

function Footer() {
    return (
        <footer className="footer">
            <div className="footer-content">
                {/* Logo ve şirket adı */}
                <div className="footer-logo">
                    <img src="./src/image/logo.png" alt="Logo" className="logo" />
                    <span>CyberAirlines</span>
                </div>

                {/* Bağlantılar */}
                <div className="footer-links">
                    <a href="#hakkimizda" className="footer-link">Hakkımızda</a>
                    <a href="#vizyon" className="footer-link">Vizyon & Misyon</a>
                    <a href="#populer-ucuslar" className="footer-link">Popüler Uçuşlar</a>
                    <a href="#yardim" className="footer-link">Yardım</a>
                </div>

                {/* Sosyal medya */}
                <div className="footer-social">
                    <span>Sosyal Medyada Bizi Takip Edin</span>
                    <div className="social-icons">
                        <a href="#" className="social-icon"><FaYoutube /></a>
                        <a href="#" className="social-icon"><FaFacebookF /></a>
                        <a href="#" className="social-icon"><FaLinkedinIn /></a>
                        <a href="#" className="social-icon"><FaInstagram /></a>
                        <a href="#" className="social-icon"><FaTwitter /></a>
                    </div>
                </div>
            </div>
        </footer>
    );
}

export default Footer;
