package com.ajeet.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ajeet.model.Admin;

@Repository
public interface AdminDao extends JpaRepository<Admin, Integer> {
	
	public Admin findByMobileNo(String mobileNo);
	
	@Query("from Admin")
	public Admin getAllData();
	

}
