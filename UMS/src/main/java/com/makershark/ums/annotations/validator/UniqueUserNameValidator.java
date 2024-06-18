package com.makershark.ums.annotations.validator;

import org.springframework.stereotype.Component;

import com.makershark.ums.annotations.UniqueUserName;
import com.makershark.ums.service.UserService;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class UniqueUserNameValidator implements ConstraintValidator<UniqueUserName, String> {

	private final UserService userService;
	
	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		return userService.doesUserNameExists(value) ? false :true;
	}

}
