package com.cyb.web.xtgl.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

public class RoleValidator   implements Validator{

	@Override
	public boolean supports(Class<?> clazz) {
		return clazz.equals(RoleValidator.class);
	}

	@Override
	public void validate(Object obj, Errors errors) {
	    ValidationUtils.rejectIfEmptyOrWhitespace(errors, "rolename", "err.rolename", "rolename is required. *");
	    ValidationUtils.rejectIfEmptyOrWhitespace(errors, "description", "err.description", "description is required. *");
	}
}
