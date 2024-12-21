package com.samilemir.jwt;

import java.util.Date;
import java.util.function.Function;

import javax.crypto.SecretKey;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Service
public class JWTService {
	
	public static final String SECRET_KEY="VUtlA+0E3RkVS0SgNNDPVMrQUV9S/G0uJid301zHngg=";
	
	public String generateToken(UserDetails userDetails)
	{
		return Jwts.builder()
		.setSubject(userDetails.getUsername())
		.setIssuedAt(new Date())
		.setExpiration(new Date(System.currentTimeMillis()+1000*60*60*1))
		.signWith(getKey(),SignatureAlgorithm.HS256)
		.compact();	
	}
	public Claims getClaims(String token) {
		return Jwts.parserBuilder()
		.setSigningKey(getKey())
		.build()
		.parseClaimsJws(token)
		.getBody();
		
	}
	
	public String getUsernameByToken(String token)
	{
		return exportToken(token, Claims::getSubject);
	}
	
	
	public boolean isTokenExpired(String token) {
	    Date expiredDate = exportToken(token, Claims::getExpiration);
	    return new Date().after(expiredDate);  // Eğer şu anki tarih son kullanma tarihinden sonrayse token süresi dolmuştur
	}
	
	 public <T> T exportToken(String token, Function<Claims, T> claimsResolver) {
	        Claims claims = Jwts
	        .parserBuilder()
	        .setSigningKey(getKey())
	        .build()
	        .parseClaimsJws(token)
	        .getBody();
	        return claimsResolver.apply(claims);
	    }
	public SecretKey getKey() {
		byte[] bytes=Decoders.BASE64.decode(SECRET_KEY);
		return Keys.hmacShaKeyFor(bytes);
	}

}
