package com.stocktracker.auth;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.password.StandardPasswordEncoder;
import org.springframework.stereotype.Component;

import com.stocktracker.service.UserService;

@Component
public class UserAuthenticationProvider implements AuthenticationProvider{
	
	Logger logger =  Logger.getLogger(UserAuthenticationProvider.class);  
	@Autowired
	private UserService userService;
	
//	@Autowired
//	private StandardPasswordEncoder passwordEncoder;

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		// TODO Auto-generated method stub
		System.out.println("Authentication");
		String userName = authentication.getName();
		String password = authentication.getCredentials().toString();
		String role = null;
		if (authorizedUser(userName, password,role))
		{
				List<GrantedAuthority> grantedAuths = new ArrayList<>();
//				grantedAuths.add(new SimpleGrantedAuthority("ROLE_USER"));
				grantedAuths.add(()-> {return "AUTH_USER";});
				Authentication auth = new UsernamePasswordAuthenticationToken(userName, password, grantedAuths);
				System.out.println(auth.getAuthorities());
				return auth;
		}
		else
		{
				throw new AuthenticationCredentialsNotFoundException("Invalid Credentials!");
		}
	}

	private boolean authorizedUser(String userName, String password, String role)
	{
			System.out.println("username is :" + userName+" and password is "+password );
//			boolean result = userService.loginAuthService(userName, password, role);
//			return result;
			if("tony".equals(userName) && "1234".equals(password))
					return true;
			return false;
		
	}

	@Override
	public boolean supports(Class<?> authentication) {
		// TODO Auto-generated method stub
		System.out.println("Authentication supports");
		return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
	}

}
