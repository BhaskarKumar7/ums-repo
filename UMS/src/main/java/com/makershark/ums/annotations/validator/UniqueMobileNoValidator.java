package com.makershark.ums.annotations.validator;

import org.springframework.stereotype.Component;

import com.makershark.ums.annotations.UniqueMobileNo;
import com.makershark.ums.service.UserService;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class UniqueMobileNoValidator implements ConstraintValidator<UniqueMobileNo, String> {

	private final UserService userService;
	
	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		return userService.doesMobileNoExists(value) ? false :true;
	}

}
