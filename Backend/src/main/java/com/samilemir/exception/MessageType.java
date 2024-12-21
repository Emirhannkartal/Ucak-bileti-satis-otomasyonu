package com.samilemir.exception;

import lombok.Getter;

@Getter
public enum MessageType {
	
	KAYİT_BULUNAMADİ("1004", "kayıt bulunamadı"),
	GENEL_HATA("9999", "genel bir hata oluştu"),
	TOKEN_SURESİ_DOLDU("1005", "token süresi bitti"),
	KULLANİCİADİ_BULUNAMADİ("1006","username bulunamadı"),
	KULLANİCİADİ_VEYA_SİFRE_HATALİ("1007", "kullanıcı adı veya şifre hatalı"),
	REFRESH_TOKEN_BULUNAMADİ("1008","refresh token bulunamadı"),
	REFRESH_TOKENİN_SURESİ_BİTMİSTİR("1009","refresh token' ın süresi bitmiştir"),
	BILET_BULUNAMADI("1010","bilet bulunamadı"),
	YOLCU_BULUNAMADI("1011","yolcu bulunamadı"),
	CHECKIN_YAPILDI("1012","checkin işlemi önceden yapılmıştır."),
	HAVAYOLU_BULUNAMADI("1013","havayolu bulunamadı"),
	HAVALANI_BULUNAMADI("1014","havaalanı bulunamadı"),
	UCUS_BULUNAMADI("1015","uçuş bulunamadı"),
	YOLCU_ZATEN_KAYITLI("1016","yolcu sisteme kayıtlı");
	
	
	
	private String code;
	
	private String message;
	
	MessageType(String code, String message){
		this.code=code;
		this.message=message;
	}
	
	

}
