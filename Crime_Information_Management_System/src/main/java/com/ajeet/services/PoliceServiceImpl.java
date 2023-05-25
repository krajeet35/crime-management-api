package com.ajeet.services;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.List;
import java.util.Optional;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ajeet.dao.CrimeDao;
import com.ajeet.dao.CriminalDao;
import com.ajeet.dao.PoliceDao;
import com.ajeet.dao.SessionDao;
import com.ajeet.exceptions.CrimeException;
import com.ajeet.exceptions.CriminalException;
import com.ajeet.exceptions.LoginException;
import com.ajeet.exceptions.PoliceException;
import com.ajeet.model.Crime;
import com.ajeet.model.Criminal;
import com.ajeet.model.CurrentUserSession;
import com.ajeet.model.LoginDTO;
import com.ajeet.model.Police;

@Service
public class PoliceServiceImpl implements PoliceService {
	
	@Autowired
	private PoliceDao cDao;
	
	@Autowired
	private SessionDao sDao;
	
	@Autowired
	private CrimeDao crDao;
	
	@Autowired
	private CriminalDao criDao;

	@Override
	public String logIn(LoginDTO dto) throws LoginException {
		Police user= cDao.findByMobileNo(dto.getMobileNo());
		
		if(user== null) {
			throw new LoginException("Mobile number not registered..");
		}
		Optional<CurrentUserSession> currentUser= sDao.findById(user.getPoliceId());
		if(currentUser.isPresent()) {
			throw new LoginException("User already loggedIn...");
		}
		if(user.getPassword().equals(dto.getPassword())) {
			String key= RandomStringUtils.random(6,String.valueOf(System.currentTimeMillis()));
			
			CurrentUserSession cus= new CurrentUserSession(user.getPoliceId(), key, LocalDateTime.now());
			sDao.save(cus);
			return cus.toString();
		}
		else {
			throw new LoginException("Enter a valid password");
		}
	}

	@Override
	public String logOut(String key) throws LoginException {
		CurrentUserSession user= sDao.findByUuid(key);
		if(user==null) {
			throw new LoginException("User not loggedIn..");
		}
		else {
			sDao.delete(user);
			return "Logged out Successfully..";
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

	
	@Override
	public Crime saveCrime(Crime crime, String key) throws CrimeException, PoliceException {
		CurrentUserSession user= sDao.findByUuid(key);
		if(user==null) {
			throw new PoliceException("Please provide valid key to save a crime");
		}
		if(user.getUserId() !=null) {
			return crDao.save(crime);
		}
		else {
			throw new PoliceException("Please login first...");
		}
	}

	@Override
	public Crime updateCrime(Crime crime, String key) throws CrimeException, PoliceException {
		CurrentUserSession user= sDao.findByUuid(key);
		if(user==null) {
			throw new PoliceException("Please provide valid key to save a crime");
		}
		if(user.getUserId() !=null) {
			Optional<Crime> oldCrime= crDao.findById(crime.getCaseNo());
			if(oldCrime.isPresent()) {
				return crDao.save(crime);
			}
			else {
				throw new CrimeException("Enter a valid case no.");
			}
		}
		else {
			throw new PoliceException("Please login first...");
		}
	}

	
	
	
	
	@Override
	public Criminal saveCriminal(Criminal criminal, String key) throws CriminalException, PoliceException {
		CurrentUserSession user= sDao.findByUuid(key);
		if(user==null) {
			throw new PoliceException("Please provide valid key to save a crime");
		}
		if(user.getUserId() !=null) {
			return criDao.save(criminal);
		}
		else {
			throw new PoliceException("Please login first...");
		}
	}

	@Override
	public Criminal updateCriminal(Criminal criminal, String key) throws CriminalException, PoliceException {
		CurrentUserSession user= sDao.findByUuid(key);
		if(user==null) {
			throw new PoliceException("Please provide valid key to save a crime");
		}
		if(user.getUserId() !=null) {
			Optional<Criminal> oldCrime= criDao.findById(criminal.getCriminalId());
			if(oldCrime.isPresent()) {
				return criDao.save(criminal);
			}
			else {
				throw new CriminalException("Enter a valid case no.");
			}
		}
		else {
			throw new PoliceException("Please login first...");
		}
	}

	@Override
	public List<Crime> searchCrimeByDescription(String value, String key) throws PoliceException, CrimeException {
		CurrentUserSession user= sDao.findByUuid(key);
		if(user==null) {
			throw new PoliceException("Enter valid key");
		}
		if(user.getUserId()!=null) {
			List<Crime> crimes= crDao.searchByDescription(value);
			if(crimes==null) {
				throw new CrimeException("No result found");
			}
			return crimes;
		}
		else {
			throw new PoliceException("Login first");
		}
	}

	@Override
	public List<Criminal> searchCriminalByName(String value, String key) throws CriminalException, PoliceException {
		CurrentUserSession user= sDao.findByUuid(key);
		if(user==null) {
			throw new PoliceException("Enter valid key");
		}
		if(user.getUserId()!=null) {
			List<Criminal> criminals= criDao.searchByName(value);
			if(criminals==null) {
				throw new CriminalException("No result found");
			}
			return criminals;
		}
		else {
			throw new PoliceException("Login first");
		}
	}

	@Override
	public List<Crime> getCrimesByCity(String city, String key) throws PoliceException, CrimeException {
		CurrentUserSession user= sDao.findByUuid(key);
		if(user==null) {
			throw new PoliceException("Enter valid key");
		}
		if(user.getUserId()!=null) {
			List<Crime> crimes= crDao.getCrimesByCity(city);
			if(crimes==null) {
				throw new CrimeException("No result found");
			}
			return crimes;
		}
		else {
			throw new PoliceException("Login first");
		}
	}

	@Override
	public List<Criminal> getCriminalsByCity(String city, String key) throws PoliceException, CriminalException {
		CurrentUserSession user= sDao.findByUuid(key);
		if(user==null) {
			throw new PoliceException("Enter valid key");
		}
		if(user.getUserId()!=null) {
			List<Criminal> criminals= criDao.getCriminalsByCity(city);
			if(criminals==null) {
				throw new CriminalException("No result found");
			}
			return criminals;
		}
		else {
			throw new PoliceException("Login first");
		}
	}

	@Override
	public List<Crime> getPendingOrSolvedCrimes(String value, String key) throws PoliceException, CrimeException {
		CurrentUserSession user= sDao.findByUuid(key);
		if(user==null) {
			throw new PoliceException("Enter valid key");
		}
		if(user.getUserId() != null) {
			List<Crime> crimes= crDao.findByCrimeStatus(value);
			if(crimes==null) {
				throw new CrimeException("No result found ***Hint use Pending or Solved keyword");
			}
			return crimes;
		}
		else {
			throw new PoliceException("login first");
		}
	}

	@Override
	public String getNoOfCrimesInCurrentMonth(String key) throws PoliceException, CrimeException {
		CurrentUserSession user= sDao.findByUuid(key);
		if(user==null) {
			throw new PoliceException("Enter valid key");
		}
		if(user.getUserId() != null) {
			String date=LocalDate.now().toString();
			LocalDate currentDate= LocalDate.parse(date);
			List<Crime> crimes= crDao.getCurrentMonthCrimes();
			if(crimes==null) {
				throw new CrimeException("No crimes registered in "+currentDate.getMonth());
			}
			return "Total No. of crimes registered in the month of "+currentDate.getMonth()+" is "+crimes.size();
		}
		else {
			throw new PoliceException("login first");
		}
	}


}
