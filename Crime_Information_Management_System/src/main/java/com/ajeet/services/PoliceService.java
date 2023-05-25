package com.ajeet.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ajeet.exceptions.CrimeException;
import com.ajeet.exceptions.CriminalException;
import com.ajeet.exceptions.LoginException;
import com.ajeet.exceptions.PoliceException;
import com.ajeet.model.Crime;
import com.ajeet.model.Criminal;
import com.ajeet.model.LoginDTO;
import com.ajeet.model.Police;

@Service
public interface PoliceService {
	
    public String logIn(LoginDTO dto) throws LoginException;
	
	public String logOut(String key) throws LoginException;
	
	public Police updateCustomer(Police customer, String key) throws PoliceException;
	
	public Crime saveCrime(Crime crime, String key) throws CrimeException, PoliceException;
	
	public Crime updateCrime(Crime crime, String key) throws CrimeException,PoliceException;
	
	public List<Crime> searchCrimeByDescription(String value, String key) throws PoliceException, CrimeException;
	
	public List<Crime> getCrimesByCity(String city, String key) throws PoliceException,CrimeException;
	
	public List<Crime> getPendingOrSolvedCrimes(String value, String key) throws PoliceException,CrimeException;
	
	public String getNoOfCrimesInCurrentMonth(String key) throws PoliceException, CrimeException;
	
	
	public Criminal saveCriminal(Criminal criminal, String key) throws CriminalException, PoliceException;
	
	public Criminal updateCriminal(Criminal criminal, String key) throws CriminalException, PoliceException;
	
	public List<Criminal> searchCriminalByName(String value, String key) throws CriminalException,PoliceException;
	
	public List<Criminal> getCriminalsByCity(String city, String key) throws PoliceException, CriminalException;


}
