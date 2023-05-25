package com.ajeet.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import com.ajeet.model.Crime;
import com.ajeet.model.Criminal;

@Service
public interface CriminalDao extends JpaRepository<Criminal, Integer> {
	
	@Query("from Criminal where name like %?1%")
	public List<Criminal> searchByName(String value);
	
	@Query("from Criminal where criminalAddress.city=?1")
	public List<Criminal> getCriminalsByCity(String city);

}
