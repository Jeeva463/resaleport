package com.example.resale.security;
import java.security.Key;
import java.util.Base64;
import java.util.Date;
import java.util.Map;
import java.util.function.Function;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.example.resale.entity.User;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtService {
	private static final String SECRET_KEY="03660bc3b1d80451fa15edf510ef9348a588f350029d5ea0d011f600e0e7b593";
	private static final long ACCESS_TOKEN_VALIDITY_SECONDS = 5*60*60*1000;


	public String extractUserName(String token) {
		return extractClaim(token, Claims::getSubject);
	}

	public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
		final Claims claims = extractAllClaims(token);
		return claimsResolver.apply(claims);
	}

	private Claims extractAllClaims(String token) {
		return Jwts.parserBuilder().setSigningKey(getSigninKey()).build().parseClaimsJws(token).getBody();
	}

	private Key getSigninKey() {
		byte[] keyBytes = Base64.getDecoder().decode(SECRET_KEY);
		return Keys.hmacShaKeyFor(keyBytes);
	}

	public String generateToken(User user) {
		return generateToken(Map.of(), user);
	}

	public String generateToken(Map<String, Object> extraClaim, User user) {
		return Jwts.builder().setClaims(extraClaim).setSubject(user.getUsername())
				.setIssuer("http://ebraintechnologies.com").setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + ACCESS_TOKEN_VALIDITY_SECONDS))
				.signWith(getSigninKey(), SignatureAlgorithm.HS256).compact();
	}

	public boolean isTokenValid(String token, User user) {
		final String userName = extractUserName(token);
		return (userName.equals(user.getUsername())) && !istokenexpired(token);
	}

	private boolean istokenexpired(String token) {
		return extractExpriation(token).before(new Date());
	}

	private Date extractExpriation(String token) {
		return extractClaim(token, Claims::getExpiration);
	}
}



