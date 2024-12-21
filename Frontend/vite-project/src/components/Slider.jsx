import React from "react";
import Slider from "react-slick";
import "slick-carousel/slick/slick.css";
import "slick-carousel/slick/slick-theme.css";
import "../css/Slider.css";

const CustomSlider = () => {
    const settings = {
        dots: true,
        infinite: false, 
        speed: 500,
        slidesToShow: 3,
        slidesToScroll: 1,
        responsive: [
            { breakpoint: 1024, settings: { slidesToShow: 2 } },
            { breakpoint: 768, settings: { slidesToShow: 1 } },
        ],
    };

    return (
        <div className="slider-container">
            <Slider {...settings}>
                <div className="slider-item">
                    <img src="./src/image/Slides.jpeg" alt="Mobil Uygulama Avantajları" />
                    <h3>Mobil Uygulama Avantajları!</h3>
                    <p>Türk Hava Yolları mobil uygulamasını indirerek avantajlardan yararlanabilirsiniz.</p>
                    <a href="#incele" className="slider-link">İncele »</a>
                </div>
                <div className="slider-item">
                    <img src="./src/image/Slides1.jpeg" alt="Kendinizi Ödüllendirin" />
                    <h3>Kendinizi Ödüllendirin!</h3>
                    <p>Milleri değerlendirin, kendinizi ödüllendirin. Bu sayfadan avantajlı biletleri alın.</p>
                    <a href="#incele" className="slider-link">İncele »</a>
                </div>
                <div className="slider-item">
                    <img src="./src/image/Slides2.jpeg" alt="İstanbul Havalimanı" />
                    <h3>İstanbul Havalimanı'nı Keşfedin!</h3>
                    <p>350’den fazla noktaya uçuş sağlayan İstanbul Havalimanı’nı yeni eviniz yapın!</p>
                    <a href="#kesfet" className="slider-link">Keşfet »</a>
                </div>
            </Slider>
        </div>
    );
};

export default CustomSlider;
