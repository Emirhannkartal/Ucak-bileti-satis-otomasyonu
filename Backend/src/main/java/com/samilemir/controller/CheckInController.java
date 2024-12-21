package com.samilemir.controller;

import com.samilemir.model.CheckIn;
import com.samilemir.service.ICheckInService;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/v1/checkin")
@Validated
public class CheckInController {

    @Autowired
    private ICheckInService checkInService;

    @PostMapping
    public String checkInPassenger(
            @Valid @RequestBody CheckIn request,
            @RequestHeader(value = "Authorization", required = true) String authorizationHeader) {
    	
        if (!authorizationHeader.startsWith("Bearer ")) {
            throw new AccessDeniedException("Authorization token is missing or invalid");
        }

        String token = authorizationHeader.substring(7);
        if (!isValidToken(token)) {
            throw new AccessDeniedException("Invalid authorization token");
        }

        return checkInService.checkInPassenger(request.getLastName(), request.getPnrCode());
    }

    private boolean isValidToken(String token) {
        return true; 
    }
}
