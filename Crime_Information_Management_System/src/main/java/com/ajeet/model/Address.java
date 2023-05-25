package com.ajeet.model;

import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Address {
	private String houseNo;
	private String city;
	private String state;
	@Size(min=10, max=10, message="{pincode.invalid}")
	private String pincode;

}
