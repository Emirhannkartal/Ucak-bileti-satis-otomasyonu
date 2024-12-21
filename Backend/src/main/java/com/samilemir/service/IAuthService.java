package com.samilemir.service;

import com.samilemir.dto.DtoUser;
import com.samilemir.jwt.AuthRequest;
import com.samilemir.jwt.AuthResponse;
import com.samilemir.jwt.RefreshTokenReq;

public interface IAuthService {
	
	public DtoUser register(AuthRequest request);
	
	public AuthResponse authenticate(AuthRequest request);
	
	public AuthResponse refreshToken(RefreshTokenReq request);
}
