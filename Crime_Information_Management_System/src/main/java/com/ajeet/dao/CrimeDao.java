package com.ajeet.dao;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ajeet.model.Crime;

@Repository
public interface CrimeDao extends JpaRepository<Crime, Integer> {
	
	@Query("from Crime where description like %?1%")
	public List<Crime> searchByDescription(String value);
	
	@Query("from Crime  where crimeAddress.city=?1")
	public List<Crime> getCrimesByCity(String city);
	
	public List<Crime> findByCrimeStatus(String value);
	
	@Query("from Crime where MONTH(date)=MONTH(LOCALTIME())")
	public List<Crime> getCurrentMonthCrimes();

}
