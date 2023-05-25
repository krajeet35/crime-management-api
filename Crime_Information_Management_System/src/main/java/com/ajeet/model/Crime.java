package com.ajeet.model;

import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Entity
public class Crime {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer caseNo;
	
	private LocalDate date;
	@Size(min=3, message="{name.invalid}")
	private String victimName;
	private String description;
	private String detailDescription;
	@Embedded
	private Address crimeAddress;
	
	private String crimeStatus;
	
	private List<String> suspects;
	
	

}
