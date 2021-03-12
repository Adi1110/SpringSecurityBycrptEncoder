package com.security.test.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.security.test.service.CustomUserDetailsService;

@Configuration
@EnableWebSecurity
public class SpringSecurityConfiguration  extends WebSecurityConfigurerAdapter{
	
	@Autowired
	CustomUserDetailsService userDetailsService;
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Override
	public void configure(AuthenticationManagerBuilder auth) throws Exception
	{
		auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
	}
	
	@Override
	protected void configure(HttpSecurity httpSecurity) throws Exception {
		// We don't need CSRF for this example
		httpSecurity.csrf().disable()
				.authorizeRequests().antMatchers("/helloadmin")
				.hasAnyRole("ADMIN", "USER")
				.antMatchers("/hellouser")
				.hasRole("USER")
				.and().httpBasic();
	}

}
