package com.stocktracker.auth;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.password.StandardPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class UserAuthenticationProvider implements AuthenticationProvider{
	
	//public Logger logger =  Logger.getLogger(UserAuthenticationProvider.class);  
	
	//private LoginDao loginDao;
	
//	@Autowired
//	private StandardPasswordEncoder passwordEncoder;

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		// TODO Auto-generated method stub
		System.out.println("Authentication");
		String userName = authentication.getName();
		String password = authentication.getCredentials().toString();

		if (authorizedUser(userName, password))
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

	private boolean authorizedUser(String userName, String password)
	{
			System.out.println("username is :" + userName+" and password is "+password );
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
