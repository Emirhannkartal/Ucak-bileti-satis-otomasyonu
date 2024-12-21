package com.samilemir.controller.impl;

import com.samilemir.jwt.AuthRequest;
import com.samilemir.jwt.AuthResponse;
import com.samilemir.jwt.RefreshTokenReq;
import com.samilemir.controller.RootEntity;
import com.samilemir.dto.DtoUser;

public interface IRestAuthController {
	
	public RootEntity<DtoUser> register(AuthRequest register);
	
	public RootEntity<AuthResponse> authenticate(AuthRequest request);
	
	public RootEntity<AuthResponse> refreshToken(RefreshTokenReq request);

}
