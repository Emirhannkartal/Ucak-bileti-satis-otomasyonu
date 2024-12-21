package com.samilemir.controller.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.samilemir.controller.IRestAuthController;
import com.samilemir.controller.RestBaseController;
import com.samilemir.controller.RootEntity;
import com.samilemir.dto.DtoUser;
import com.samilemir.jwt.AuthRequest;
import com.samilemir.jwt.AuthResponse;
import com.samilemir.jwt.RefreshTokenReq;
import com.samilemir.service.IAuthService;

import jakarta.validation.Valid;


@RestController
public class RestAuthControllerImpl extends RestBaseController implements IRestAuthController {

	
	@Autowired
	private IAuthService authService;
	
	@PostMapping("/register")
	@Override
	public RootEntity <DtoUser> register(@Valid @RequestBody AuthRequest request) {
		return ok(authService.register(request));
	}
	
	@PostMapping("/authenticate")
	@Override
	public RootEntity<AuthResponse> authenticate(@Valid @RequestBody AuthRequest request) {
		// TODO Auto-generated method stub
		return ok(authService.authenticate(request));
	}
	
	@PostMapping("/refreshToken")
	@Override
	public RootEntity<AuthResponse> refreshToken(@Valid @RequestBody RefreshTokenReq request) {
		// TODO Auto-generated method stub
		return ok(authService.refreshToken(request));
	}
	

}
