package com.TeamSeven.CConge.security;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import com.TeamSeven.CConge.domain.User;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;

import static com.TeamSeven.CConge.security.SecurityConstants.SECRETE;
import static com.TeamSeven.CConge.security.SecurityConstants.EXPIRATIONTIME;

@Component
public class JwtTokenProvider {

	//Generate a token
	public String generateToken(Authentication authentication) {
		User user = (User) authentication.getPrincipal();
		Date now = new Date(System.currentTimeMillis()); 
		Date expiredDate = new Date(now.getTime() + EXPIRATIONTIME); 
		
		String userId = Long.toString(user.getId());
		
		Map<String,Object> claims = new HashMap<>();
		claims.put("id",(Long.toString(user.getId())) );
		claims.put("username", user.getUsername());
		claims.put("fullname", user.getFullName());
		claims.put("isAdmin", Boolean.toString(user.getIsAdmin()));
		
		return Jwts.builder()
				.setSubject(userId)
				.setClaims(claims)
				.setIssuedAt(now)
				.setExpiration(expiredDate)
				.signWith(SignatureAlgorithm.HS512, SECRETE)
				.compact();
				
	}
	
	//validate token
	public boolean validateToken(String token) {
		try {
			Jwts.parser().setSigningKey(SECRETE).parseClaimsJws(token);
			return true;
		}catch(SignatureException ex) {
			System.out.println("Invalide JWT Token");
		}catch(MalformedJwtException ex) {
			System.out.println("Invalide JWT Token");
		}catch(ExpiredJwtException ex) {
			System.out.println("Expired JWT Token");
		}catch(UnsupportedJwtException ex) {
			System.out.println("Unsupported JWT Token");
		}
		catch(IllegalArgumentException ex) {
			System.out.println(" JWT claims string is empty");
		}
		return false;
	}
	
	
	
	//Get user id from token
	public Long getUserIdFromJWT(String token) {
		Claims claims = Jwts.parser().setSigningKey(SECRETE).parseClaimsJws(token).getBody();
		String id = (String) claims.get("id");
		return Long.parseLong(id);
	}
	
}
