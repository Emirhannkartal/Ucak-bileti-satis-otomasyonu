package com.samilemir.repos;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.samilemir.model.RefreshToken;

@Repository
public interface RefreshTokenRepos extends JpaRepository<RefreshToken, Long> {
	
	Optional <RefreshToken> findByRefreshToken(String refreshToken);

}
