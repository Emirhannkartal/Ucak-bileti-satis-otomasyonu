package com.samilemir.jwt;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JWTAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private JWTService jwtService;

    @Autowired
    private UserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        
        String header = request.getHeader("Authorization");
        
        // Eğer Authorization header'ı yoksa veya "Bearer " ile başlamıyorsa, isteği geç
        if (header == null || !header.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        // Token'ı "Bearer " kısmından ayıklıyoruz
        String token = header.substring(7);
        
        try {
            // Token'dan kullanıcı adı alınıyor
            String username = jwtService.getUsernameByToken(token);

            // Eğer username geçerliyse ve SecurityContext boşsa, kimlik doğrulaması yapılır
            if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                UserDetails userDetails = userDetailsService.loadUserByUsername(username);

                // Eğer token süresi dolmamışsa, kimlik doğrulaması yapılır
                if (!jwtService.isTokenExpired(token)) {
                    UsernamePasswordAuthenticationToken authentication = 
                            new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                    
                    // Detaylar ekleniyor ve SecurityContext'e atanıyor
                    authentication.setDetails(userDetails);
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                } else {
                    // Token süresi dolmuşsa log yazılabilir veya başka bir işlem yapılabilir
                    System.out.println("Token süresi dolmuş.");
                }
            }
        } catch (ExpiredJwtException e) {
            // Token süresi dolmuşsa, burada bir hata mesajı verilebilir
            System.out.println("Token süresi doldu: " + e.getMessage());
        } catch (JwtException e) {
            // Eğer JWT ile ilgili başka bir hata varsa, burada log yazılabilir
            System.out.println("JWT doğrulama hatası: " + e.getMessage());
        } catch (Exception e) {
            // Diğer her türlü hatayı logla
            System.out.println("Hata: " + e.getMessage());
        }

        // Filtre zincirinin devam etmesi sağlanıyor
        filterChain.doFilter(request, response);
    }
}
