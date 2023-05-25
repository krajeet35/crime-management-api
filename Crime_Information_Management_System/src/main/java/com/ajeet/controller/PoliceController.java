package com.ajeet.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ajeet.exceptions.CrimeException;
import com.ajeet.exceptions.CriminalException;
import com.ajeet.exceptions.LoginException;
import com.ajeet.exceptions.PoliceException;
import com.ajeet.model.Crime;
import com.ajeet.model.Criminal;
import com.ajeet.model.LoginDTO;
import com.ajeet.model.Police;
import com.ajeet.services.PoliceService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/")
public class PoliceController {
	
	@Autowired
	private PoliceService pService;
	
	
	@PostMapping("/login")
	public ResponseEntity<String> logInCustomer(@RequestBody LoginDTO dto) throws LoginException{
		String result= pService.logIn(dto);
		
		return new ResponseEntity<String>(result,HttpStatus.OK);
	}
	
	@PostMapping("/logout")
	public String logoutCustomer(@RequestParam(required = false) String key) throws LoginException {
		return pService.logOut(key);
	}
	
	@PutMapping("/update")
	public ResponseEntity<Police> updateCustomerHandler(@Valid @RequestBody Police customer, @RequestParam String key) throws PoliceException{
		Police updatedCustomer= pService.updateCustomer(customer, key);
		return new ResponseEntity<Police>(updatedCustomer, HttpStatus.OK);
	}
	
	@PostMapping("/saveCrime")
	public ResponseEntity<Crime> saveCrimeHandler(@Valid @RequestBody Crime crime, @RequestParam String key) throws CrimeException, PoliceException{
		Crime newCrime= pService.saveCrime(crime, key);
		return new ResponseEntity<Crime>(newCrime, HttpStatus.CREATED);
	}
	@PutMapping("/updateCrime")
	public ResponseEntity<Crime> updateCrimeHandler(@Valid @RequestBody Crime crime, @RequestParam String key) throws CrimeException, PoliceException{
		Crime updatedCrime= pService.updateCrime(crime, key);
		return new ResponseEntity<Crime>(updatedCrime, HttpStatus.ACCEPTED);
	}
	@PostMapping("/saveCriminal")
	public ResponseEntity<Criminal> saveCriminalHandler(@Valid @RequestBody Criminal criminal, @RequestParam String key) throws CriminalException, PoliceException{
		Criminal newCriminal= pService.saveCriminal(criminal, key);
		return new ResponseEntity<Criminal>(newCriminal, HttpStatus.CREATED);
	}
	@PutMapping("/updateCriminal")
	public ResponseEntity<Criminal> updateCriminalHandler(@Valid @RequestBody Criminal criminal, @RequestParam String key) throws CriminalException, PoliceException{
		Criminal updatedCriminal= pService.updateCriminal(criminal, key);
		return new ResponseEntity<Criminal>(updatedCriminal, HttpStatus.ACCEPTED);
	}
	
	@GetMapping("/searchCrimeByDescription")
	public ResponseEntity<List<Crime>> searchCrimeByDescriptionHandler(@RequestParam("value") String value, @RequestParam("key") String key) throws PoliceException, CrimeException{
		List<Crime> crimes= pService.searchCrimeByDescription(value, key);
		return new ResponseEntity<List<Crime>>(crimes,HttpStatus.OK);
	}
	@GetMapping("/searchCriminalByName")
	public ResponseEntity<List<Criminal>> searchCriminalByNameHandler(@RequestParam("value") String value, @RequestParam("key") String key) throws PoliceException, CriminalException{
		List<Criminal> criminals= pService.searchCriminalByName(value, key);
		return new ResponseEntity<List<Criminal>>(criminals,HttpStatus.OK);
	}
	@GetMapping("/crimesInCurrentMonth")
	public ResponseEntity<String> crimesInCurrentMonthHandler(@RequestParam String key) throws PoliceException, CrimeException{
		String result= pService.getNoOfCrimesInCurrentMonth(key);
		return new ResponseEntity<String>(result,HttpStatus.OK);
	}
	
	@GetMapping("/getCrimeByCity")
	public ResponseEntity<List<Crime>> getCrimesByCityHandler(@RequestParam("city") String city, @RequestParam("key") String key) throws PoliceException, CrimeException{
		List<Crime> crimes= pService.getCrimesByCity(city, key);
		return new ResponseEntity<List<Crime>>(crimes,HttpStatus.OK);
	}
	@GetMapping("/getCriminalsByCity")
	public ResponseEntity<List<Criminal>> getCriminalsByCityHandler(@RequestParam("city") String city, @RequestParam("key") String key) throws PoliceException, CrimeException, CriminalException{
		List<Criminal> crimes= pService.getCriminalsByCity(city, key);
		return new ResponseEntity<List<Criminal>>(crimes,HttpStatus.OK);
	}
	
	@GetMapping("/getPendingOrSolvedCases")
	public ResponseEntity<List<Crime>> getPendingOrSolvedCasesHandler(@RequestParam("value") String value, @RequestParam("key") String key) throws PoliceException, CrimeException{
		List<Crime> cases= pService.getPendingOrSolvedCrimes(value, key);
		return new ResponseEntity<List<Crime>>(cases,HttpStatus.OK);
	}

}
