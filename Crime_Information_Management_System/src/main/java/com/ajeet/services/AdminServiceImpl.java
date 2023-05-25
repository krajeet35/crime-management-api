package com.ajeet.services;

import java.time.LocalDateTime;
import java.util.Optional;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ajeet.dao.AdminDao;
import com.ajeet.dao.AdminSession;
import com.ajeet.dao.PoliceDao;
import com.ajeet.dao.SessionDao;
import com.ajeet.exceptions.AdminException;
import com.ajeet.exceptions.LoginException;
import com.ajeet.exceptions.PoliceException;
import com.ajeet.model.Admin;
import com.ajeet.model.CurrentAdminSession;
import com.ajeet.model.CurrentUserSession;
import com.ajeet.model.LoginDTO;
import com.ajeet.model.Police;

@Service
public class AdminServiceImpl implements AdminService {
	@Autowired
	private AdminDao aDao;
	
	@Autowired
	private AdminSession asDao;
	@Autowired
	private PoliceDao pDao;

	@Override
	public String logIn(LoginDTO dto) throws LoginException {
        Admin user= aDao.findByMobileNo(dto.getMobileNo());
		
		if(user== null) {
			throw new LoginException("Mobile number not registered..");
		}
		Optional<CurrentAdminSession> currentUser= asDao.findById(user.getAdminId());
		if(currentUser.isPresent()) {
			throw new LoginException("User already loggedIn...");
		}
		if(user.getPassword().equals(dto.getPassword())) {
			String key= RandomStringUtils.random(8,String.valueOf(System.currentTimeMillis()));
			
			CurrentAdminSession cus= new CurrentAdminSession(user.getAdminId(), key, LocalDateTime.now());
			asDao.save(cus);
			return cus.toString();
		}
		else {
			throw new LoginException("Enter a valid password");
		}
	}

	@Override
	public String logOut(String key) throws LoginException {
		CurrentAdminSession user= asDao.findByUuid(key);
		if(user==null) {
			throw new LoginException("User not loggedIn..");
		}
		else {
			asDao.delete(user);
			return "Logged out Successfully..";
		}
	}

	@Override
	public Police createCustomer(Police customer, String key) throws PoliceException {
		CurrentAdminSession user= asDao.findByUuid(key);
		if(user==null) {
			throw new PoliceException("Please provide a valid key to save police");
		}
		Police exist= pDao.findByMobileNo(customer.getMobileNo());
		
		if(exist != null) {
			throw new PoliceException("Mobile number already registered");
		}
		else {
			return pDao.save(customer);
		}
	}

	@Override
	public Admin registerAdmin(Admin admin) throws AdminException {
		Admin user= aDao.getAllData();
		if(user!=null) {
			throw new AdminException("Admin already registered");
		}
		return aDao.save(admin);
	}

}
