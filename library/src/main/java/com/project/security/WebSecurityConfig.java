package com.project.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import com.project.security.common.SecurityConstants;
import com.project.security.filter.JWTAuthenticationFilter;
import com.project.security.filter.JWTAuthorizationFilter;
import com.project.security.model.SecurityUserRole;
import com.project.security.service.SecurityUserService;


@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{

	@Autowired
	private JWTAuthorizationFilter jwtAuthorizationFilter;
	
	@Autowired
	private SecurityUserService userService;
	
	@Autowired
	private PasswordEncoder passwordEncoder;

	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
	    return super.authenticationManagerBean();
	}
	 
	 
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
	       http.cors().and().csrf().disable().authorizeRequests()
           					.antMatchers(HttpMethod.POST, SecurityConstants.SIGN_IN_URL).permitAll()
           					.antMatchers(HttpMethod.POST, SecurityConstants.LOG_IN_URL).permitAll()
           					.antMatchers(HttpMethod.GET, "/user/loged").permitAll()
           					.antMatchers(HttpMethod.GET, "/book/*").permitAll()
           					.antMatchers(HttpMethod.POST, "/book/*").hasRole(SecurityUserRole.ADMIN.name())
           					.antMatchers(HttpMethod.PUT, "/book/*").hasRole(SecurityUserRole.ADMIN.name())
           					.antMatchers(HttpMethod.DELETE, "/book/*").hasRole(SecurityUserRole.ADMIN.name())
           					.antMatchers(HttpMethod.GET, "/bookshelf/*").permitAll()
           					.antMatchers(HttpMethod.POST, "/bookshelf/*").hasRole(SecurityUserRole.ADMIN.name())
           					.antMatchers(HttpMethod.DELETE, "/bookshelf/*").hasRole(SecurityUserRole.ADMIN.name())
           					.anyRequest().authenticated()
           				.and()
           					.addFilter(new JWTAuthenticationFilter(authenticationManagerBean()))
           					.addFilterBefore(jwtAuthorizationFilter, BasicAuthenticationFilter.class)
           				.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
	}
	
	@Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService).passwordEncoder(passwordEncoder);
    }
}
