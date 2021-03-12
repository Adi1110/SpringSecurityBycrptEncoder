package com.security.test.service;

import java.util.Arrays;
import java.util.List;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService{

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		List<SimpleGrantedAuthority> roles=null;
		if(username.equals("admin"))
		{
		roles = Arrays.asList(new SimpleGrantedAuthority("ROLE_ADMIN"));
		return new User("admin", "$2y$10$Is6.kFwNFQWqxu2K8L12keNiJZ/Lh8sVYTBm2jaIaZN/g5MNi7gPa",
					roles);
		}
		else if(username.equals("user"))
		{
		roles = Arrays.asList(new SimpleGrantedAuthority("ROLE_USER"));
		return new User("user", "$2y$10$g01h1kILuOORD3Cub1lT/ujBUvS76BX5Vq2FwOnRusqaAcCm/OxUW",
					roles);
		}
		throw new UsernameNotFoundException("User not found with username: " + username);
	}

}
