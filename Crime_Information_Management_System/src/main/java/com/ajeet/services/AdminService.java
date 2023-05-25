package com.ajeet.services;

import org.springframework.stereotype.Service;

import com.ajeet.exceptions.AdminException;
import com.ajeet.exceptions.LoginException;
import com.ajeet.exceptions.PoliceException;
import com.ajeet.model.Admin;
import com.ajeet.model.LoginDTO;
import com.ajeet.model.Police;
@Service
public interface AdminService {
	
    public String logIn(LoginDTO dto) throws LoginException;
	
	public String logOut(String key) throws LoginException;
	
	public Police createCustomer(Police customer, String key) throws PoliceException;
	
	public Admin registerAdmin(Admin admin) throws AdminException;
	

}
