package com.project.security.filter;

import static com.project.security.common.SecurityConstants.HEADER_STRING;
import static com.project.security.common.SecurityConstants.TOKEN_PREFIX;
import static com.project.security.filter.jwt.JWTTokenProvider.validateToken;

import java.io.IOException;

import javax.naming.AuthenticationException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.project.security.common.SecurityConstants;
import com.project.security.model.SecurityUser;
import com.project.security.service.SecurityUserService;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Component
@WebFilter
public class JWTAuthorizationFilter extends OncePerRequestFilter {

	@Autowired
	private SecurityUserService userService;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		String header = request.getHeader(SecurityConstants.HEADER_STRING);

        if (header == null || !header.startsWith(TOKEN_PREFIX)) {
            chain.doFilter(request, response);
            return;
        }

        UsernamePasswordAuthenticationToken authentication = getAuthentication(request);
        //ToDo 
        //Manage exceptions
        authentication.setDetails(new WebAuthenticationDetails(request));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        chain.doFilter(request, response);
	}
	
    private UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest request) {
        String token = request.getHeader(HEADER_STRING).replace(SecurityConstants.TOKEN_PREFIX, "");
        UsernamePasswordAuthenticationToken upat = null;
        
        if (token != null && !token.isEmpty() && validateToken(token)) {
        	try {
        		Integer idUser = Integer.valueOf(Jwts.parser().
        									setSigningKey(Keys.hmacShaKeyFor(SecurityConstants.SECRET.getBytes()))
        									.parseClaimsJws(token)
        									.getBody()
        									.getId());
        	
				SecurityUser user = (SecurityUser) userService.loadUserById(idUser);
				if (idUser != null) {
					upat=  new UsernamePasswordAuthenticationToken(idUser, user.getRoles(), user.getAuthorities());
				}
			} catch (AuthenticationException e) {
				throw new RuntimeException("No user identifier has been found in the request");
			}
        }
        return upat;	
    }


}
