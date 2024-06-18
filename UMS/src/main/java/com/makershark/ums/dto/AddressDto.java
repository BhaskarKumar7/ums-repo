package com.makershark.ums.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class AddressDto {

	@NotBlank
	private String addressLine;
	@NotBlank
	private String country;
	@NotBlank
	private String state;
	@NotBlank
	private String city;
	@Pattern(regexp = "^[1-9]\\d{5}$")
	private String pincode;
	@NotBlank
	private String doorNo;
}
