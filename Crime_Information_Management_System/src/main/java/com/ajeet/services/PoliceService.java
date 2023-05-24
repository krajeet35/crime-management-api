package com.ajeet.services;

import org.springframework.stereotype.Service;


import com.ajeet.exceptions.PoliceException;
import com.ajeet.model.Police;

@Service
public interface PoliceService {
	
	public Police createCustomer(Police customer) throws PoliceException;
	
	public Police updateCustomer(Police customer, String key) throws PoliceException;


}
