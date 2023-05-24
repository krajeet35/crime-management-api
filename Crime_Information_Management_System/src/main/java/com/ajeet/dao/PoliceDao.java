package com.ajeet.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ajeet.model.Police;
@Repository
public interface PoliceDao extends JpaRepository<Police, Integer> {
	
	public Police findByMobileNo(String mobileNo);

}
