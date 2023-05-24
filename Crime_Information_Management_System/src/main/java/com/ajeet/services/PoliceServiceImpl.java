package com.ajeet.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.ajeet.dao.PoliceDao;
import com.ajeet.dao.SessionDao;

import com.ajeet.exceptions.PoliceException;
import com.ajeet.model.CurrentUserSession;

import com.ajeet.model.Police;

@Service
public class PoliceServiceImpl implements PoliceService {
	
	@Autowired
	private PoliceDao cDao;
	
	@Autowired
	private SessionDao sDao;

	@Override
	public Police createCustomer(Police customer) throws PoliceException {
		Police exist= cDao.findByMobileNo(customer.getMobileNo());
		
		if(exist != null) {
			throw new PoliceException("Mobile number already registered");
		}
		else {
			return cDao.save(customer);
		}
	}

	@Override
	public Police updateCustomer(Police customer, String key) throws PoliceException {
		CurrentUserSession user= sDao.findByUuid(key);
		if(user==null) {
			throw new PoliceException("Please provide a valid key to update customer");
		}
		if(user.getUserId()==customer.getPoliceId()) {
			return cDao.save(customer);
		}
		else {
			throw new PoliceException("Please login first...");
		}
	}

}
