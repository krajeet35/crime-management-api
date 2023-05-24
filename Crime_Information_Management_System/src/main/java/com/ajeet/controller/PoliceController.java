package com.ajeet.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import com.ajeet.exceptions.PoliceException;

import com.ajeet.model.Police;

import com.ajeet.services.PoliceService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/customers")
public class PoliceController {
	
	@Autowired
	private PoliceService cService;
	
	@PostMapping("/save")
	public ResponseEntity<Police> saveCustomerHandler(@Valid @RequestBody Police police) throws PoliceException{
		Police c1= cService.createCustomer(police);
		return new ResponseEntity<Police>(c1, HttpStatus.CREATED);
	}
	
	@PutMapping("/update")
	public ResponseEntity<Police> updateCustomerHandler(@Valid @RequestBody Police customer, @RequestParam String key) throws PoliceException{
		Police updatedCustomer= cService.updateCustomer(customer, key);
		return new ResponseEntity<Police>(updatedCustomer, HttpStatus.OK);
	}

}
