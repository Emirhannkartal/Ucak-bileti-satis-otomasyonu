package com.samilemir.service.impl;


import java.util.Date;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.samilemir.dto.DtoUser;
import com.samilemir.exception.BaseException;
import com.samilemir.exception.ErrorMessage;
import com.samilemir.exception.MessageType;
import com.samilemir.jwt.AuthRequest;
import com.samilemir.jwt.AuthResponse;
import com.samilemir.jwt.JWTService;
import com.samilemir.jwt.RefreshTokenReq;
import com.samilemir.model.RefreshToken;
import com.samilemir.model.User;
import com.samilemir.repos.RefreshTokenRepos;
import com.samilemir.repos.UserRepos;
import com.samilemir.service.IAuthService;

@Service
public class AuthServiceImpl implements IAuthService {
	
	@Autowired
	private UserRepos userRepos;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	@Autowired
	private AuthenticationProvider authenticationProvider;
	
	@Autowired
	private JWTService jwtService;
	
	@Autowired
	private RefreshTokenRepos refreshTokenRepos;
	
	private User createUser(AuthRequest request) {
		User user=new User();
		user.setUsername(request.getUsername());
		user.setPassword(passwordEncoder.encode(request.getPassword()));
		return user;
	}
	
	private RefreshToken createRefreshToken(User user) {
		RefreshToken refreshToken=new RefreshToken();
		refreshToken.setCreateTime(new Date());
		refreshToken.setExpiredDate(new Date(System.currentTimeMillis() + 1000*60*60*2 ));
		refreshToken.setRefreshToken(UUID.randomUUID().toString());
		refreshToken.setUser(user);
		return refreshToken;
	}
	
	
	@Override
	public DtoUser register(AuthRequest request) {
		DtoUser dto=new DtoUser();

		User savedUser=userRepos.save(createUser(request));
		BeanUtils.copyProperties(savedUser, dto);
		return dto;
	}

	@Override
	public AuthResponse authenticate(AuthRequest request) {
		try {
			UsernamePasswordAuthenticationToken authenticationToken=new UsernamePasswordAuthenticationToken(request.getUsername(),request.getPassword());
			authenticationProvider.authenticate(authenticationToken);
			
			Optional<User> optUser = userRepos.findByUsername(request.getUsername());
			
			String accessToken =jwtService.generateToken(optUser.get());
			
			
			RefreshToken savedRefreshToken = refreshTokenRepos.save(createRefreshToken(optUser.get()));
			
			return new AuthResponse(accessToken,savedRefreshToken.getRefreshToken());
			
		}catch(Exception e) {
			throw new BaseException(new ErrorMessage(e.getMessage(), MessageType.KULLANİCİADİ_VEYA_SİFRE_HATALİ));
			
		}
	}
	
	public boolean isValidRefreshToken(Date expiredDate) {
		return new Date().before(expiredDate);
	}
	
	public AuthResponse refreshToken(RefreshTokenReq request) {
		
		Optional<RefreshToken> optRefreshToken=refreshTokenRepos.findByRefreshToken(request.getRefreshToken());
		if(optRefreshToken.isEmpty()) {
			throw new BaseException(new ErrorMessage(request.getRefreshToken(),MessageType.REFRESH_TOKEN_BULUNAMADİ));
		}
		
		if(!isValidRefreshToken(optRefreshToken.get().getExpiredDate())) {
			throw new BaseException(new ErrorMessage(request.getRefreshToken(),MessageType.REFRESH_TOKENİN_SURESİ_BİTMİSTİR));
		}
		
		User user= optRefreshToken.get().getUser();
		String accessToken=jwtService.generateToken(user);
		RefreshToken savedRefreshToken=refreshTokenRepos.save(createRefreshToken(user));
		
		return new AuthResponse(accessToken,savedRefreshToken.getRefreshToken());
		
	}
	
	


	
	
	}

