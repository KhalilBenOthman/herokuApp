package com.TeamSeven.CConge.security;

public class SecurityConstants {

	public static final String SIGN_UP_URLS = "/api/users/**";
	public static final String H2_URL = "h2-console/**";
	public static final String SECRETE ="SecretKeyToGenerateJWTs";
	public static final String TOKEN_PREFIX = "Bearer ";
	public static final String HEADER_STRING = "Authorization";
	public static final Long EXPIRATIONTIME = (long) 300_000_000 ;
	
}
