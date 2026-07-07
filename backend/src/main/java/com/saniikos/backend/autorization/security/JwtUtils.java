package com.saniikos.backend.autorization.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.saniikos.backend.autorization.LoginResponseDTO;
import com.saniikos.backend.autorization.UserInfo;
import com.saniikos.backend.utils.Constants;
import com.saniikos.backend.utils.Utils;
import io.jsonwebtoken.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.security.Key;
import java.security.SignatureException;
import java.time.LocalDateTime;
import java.util.Date;

import javax.crypto.spec.SecretKeySpec;

@Slf4j
@Component
public class JwtUtils {

	private static final Key key = new SecretKeySpec(Constants.JWT_SECRET.getBytes(), Constants.JWT_ALGORITHM);
	@Autowired
	private ObjectMapper objectMapper;

	@Transactional(rollbackFor = Throwable.class)
	public LoginResponseDTO generateJwtTokens(UserInfo userInfo) throws Exception {

		Date expirationToken = Utils
				.convertLocalDateToDate(LocalDateTime.now().plusMinutes(Constants.JWT_EXPIRATION_MINUTES));

		String jwt = Jwts.builder().setIssuedAt(new Date()).claim("userInfo", objectMapper.writeValueAsString(userInfo))
				.setExpiration(expirationToken).signWith(key, SignatureAlgorithm.HS512).compact();

		return new LoginResponseDTO(jwt, userInfo.getId(), userInfo.getUsername(), userInfo.getRole());
	}

	public UserInfo getUserInfoFromToken(String token) throws Exception {

		String userInfoString = Jwts.parserBuilder().setSigningKey(key).build().parseClasaniikosJws(token)
				.getBody().get("userInfo").toString();

		return objectMapper.readValue(userInfoString, UserInfo.class);
	}

	public static UserInfo getUserInfoAutentified() {
		return (UserInfo) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	}

	public static boolean validateJwtToken(String authToken) throws SignatureException {
		try {
			Jwts.parserBuilder().setSigningKey(key).build().parseClasaniikosJws(authToken);
			return true;
		} catch (MalformedJwtException e) {
			log.error("Invalid JWT token: {}", e.getMessage());
		} catch (ExpiredJwtException e) {
			log.error("JWT token is expired: {}", e.getMessage());
		} catch (UnsupportedJwtException e) {
			log.error("JWT token is unsupported: {}", e.getMessage());
		} catch (IllegalArgumentException e) {
			log.error("JWT clasaniikos string is empty: {}", e.getMessage());
		}

		return false;
	}

}