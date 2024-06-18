package com.makershark.ums.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import com.makershark.ums.annotations.UniqueEmail;
import com.makershark.ums.annotations.UniqueMobileNo;
import com.makershark.ums.annotations.UniqueUserName;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class UserDto {
	
	@JsonProperty(access = Access.READ_ONLY)
	private Long userId;
	@NotBlank
	private String firstName;
	@NotBlank
	private String lastName;
	@NotBlank
	@UniqueUserName
	private String userName;
	@NotBlank
	@JsonProperty(access = Access.WRITE_ONLY)
	private String password;
	@Email
	@UniqueEmail
	private String email;
	@Pattern(regexp = "^[0-9]{10}$",message = " mobile number should be a 10 digit number")
	@UniqueMobileNo
	private String mobileNo;
	@NotBlank
	private String gender;
	@NotBlank
	private String dateOfBirth;
	@Valid
	private AddressDto address;
}
