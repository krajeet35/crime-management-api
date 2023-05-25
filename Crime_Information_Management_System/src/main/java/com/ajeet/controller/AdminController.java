package com.ajeet.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ajeet.exceptions.AdminException;
import com.ajeet.exceptions.LoginException;
import com.ajeet.exceptions.PoliceException;
import com.ajeet.model.Admin;
import com.ajeet.model.LoginDTO;
import com.ajeet.model.Police;
import com.ajeet.services.AdminService;

import com.ajeet.services.PoliceService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/")
public class AdminController {
	
	
	
	@Autowired
	private AdminService aService;
	
	@PostMapping("/registerAdmin")
	public ResponseEntity<Admin> registerAdminHandler(@Valid @RequestBody Admin admin) throws PoliceException, AdminException{
		Admin user= aService.registerAdmin(admin);
		return new ResponseEntity<Admin>(user, HttpStatus.CREATED);
	}
	
	@PostMapping("/adminLogin")
	public ResponseEntity<String> logInAdmin(@RequestBody LoginDTO dto) throws LoginException{
		String result= aService.logIn(dto);
		
		return new ResponseEntity<String>(result,HttpStatus.OK);
	}
	
	@PostMapping("/adminLogout")
	public String logoutAdmin(@RequestParam(required = false) String key) throws LoginException {
		return aService.logOut(key);
	}
	
	@PostMapping("/savePolice")
	public ResponseEntity<Police> saveCustomerHandler(@Valid @RequestBody Police police,@RequestParam("key") String key) throws PoliceException{
		Police c1= aService.createCustomer(police,key);
		return new ResponseEntity<Police>(c1, HttpStatus.CREATED);
	}
	

}
