package com.project.security.common;

public class SecurityConstants {

    public static final String 	SECRET 					= "YourKeyToGenJWT_isUp2U_@_YourKeyToGenJWT_isUp2U_@_YourKeyToGenJWT_isUp2U";
    
    public static final long 	EXPIRATION_TIME 		= 604_000_000; 
    public static final String 	TOKEN_PREFIX 			= "Bearer ";
    public static final String 	HEADER_STRING 			= "Authorization";
    public static final String 	SIGN_IN_URL 			= "/user/sign-in";
    public static final String 	LOG_IN_URL 				= "/user/log-in";
}
