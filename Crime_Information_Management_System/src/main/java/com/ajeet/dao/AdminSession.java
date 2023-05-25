package com.ajeet.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ajeet.model.CurrentAdminSession;

public interface AdminSession extends JpaRepository<CurrentAdminSession, Integer> {
	
	public CurrentAdminSession findByUuid(String uuid);

}
