package com.saniikos.backend.authorization.security;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.time.Instant;
import java.util.Date;

import javax.crypto.spec.SecretKeySpec;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.saniikos.backend.authorization.LoginResponseDTO;
import com.saniikos.backend.authorization.UserInfo;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@RequiredArgsConstructor
public class JwtUtils {

	@Value("${jwt.secret}")
	private String jwtSecret;

	@Value("${jwt.expiration-minutes}")
	private long jwtExpirationMinutes;

	private final ObjectMapper objectMapper;

	private Key getSigningKey() {

		byte[] keyBytes = jwtSecret.getBytes(StandardCharsets.UTF_8);

		if (keyBytes.length < 64) {
			throw new IllegalArgumentException(
					"JWT secret must have at least 64 bytes for HS512");
		}

		return Keys.hmacShaKeyFor(keyBytes);
	}

	public LoginResponseDTO generateJwtTokens(UserInfo userInfo) throws Exception {

		Instant now = Instant.now();

		Instant expiration = now.plusSeconds(jwtExpirationMinutes * 60);

		String token = Jwts.builder()
				.setIssuedAt(Date.from(now))
				.setExpiration(Date.from(expiration))
				.claim("userInfo", objectMapper.writeValueAsString(userInfo))
				.signWith(getSigningKey(), SignatureAlgorithm.HS512)
				.compact();

		return new LoginResponseDTO(
				token,
				userInfo.getId(),
				userInfo.getUsername(),
				userInfo.getRoles());
	}

	public UserInfo getUserInfoFromToken(String token) throws Exception {

		String json = Jwts.parserBuilder()
				.setSigningKey(getSigningKey())
				.build()
				.parseClaimsJws(token)
				.getBody()
				.get("userInfo", String.class);

		return objectMapper.readValue(json, UserInfo.class);
	}

	public static UserInfo getAuthenticatedUser() {

		if (SecurityContextHolder.getContext().getAuthentication() == null) {
			return null;
		}

		return (UserInfo) SecurityContextHolder
				.getContext()
				.getAuthentication()
				.getPrincipal();
	}

	public boolean validateJwtToken(String token) {

		try {

			Jwts.parserBuilder()
					.setSigningKey(getSigningKey())
					.build()
					.parseClaimsJws(token);

			return true;

		} catch (MalformedJwtException e) {
			log.error("Invalid JWT token", e);

		} catch (ExpiredJwtException e) {
			log.error("Expired JWT token", e);

		} catch (UnsupportedJwtException e) {
			log.error("Unsupported JWT token", e);

		} catch (IllegalArgumentException e) {
			log.error("JWT claims string is empty", e);

		} catch (Exception e) {
			log.error("JWT validation error", e);
		}

		return false;
	}

}