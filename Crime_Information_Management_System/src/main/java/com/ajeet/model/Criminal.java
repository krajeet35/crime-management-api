package com.ajeet.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.OneToMany;
import lombok.Data;

@Data
@Entity
public class Criminal {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer criminalId;
	private String name;
	private Integer age;
	private String gender;
	@Embedded
	private Address criminalAddress;
	private String faceIdentityMark;
	private String firstArrestedArea;
	
	
	@OneToMany(cascade = CascadeType.ALL)
	@JoinTable(name="criminal_crime", joinColumns = @JoinColumn(name="cId"), inverseJoinColumns = @JoinColumn(name="caseId"))
	private List<Crime> crimes= new ArrayList<>();

}
