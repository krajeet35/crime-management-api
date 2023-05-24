package com.ajeet.services;

import org.springframework.stereotype.Service;

import com.ajeet.exceptions.LoginException;
import com.ajeet.model.LoginDTO;

@Service
public interface LoginService {
	
	public String logIn(LoginDTO dto) throws LoginException;
	
	public String logOut(String key) throws LoginException;

}
