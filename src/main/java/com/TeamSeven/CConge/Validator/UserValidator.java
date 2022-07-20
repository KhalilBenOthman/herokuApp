package com.TeamSeven.CConge.Validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.TeamSeven.CConge.domain.User;

@Component
public class UserValidator implements Validator{

	@Override
	public boolean supports(Class<?> clazz) {
		return User.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		User user = (User)target;
		if(user.getPassword().length() < 6) {
			errors.rejectValue("password", "Length","mot de passe taille doit etre superieur ou egale à 6 caractere");
		}
		
		if(!user.getPassword().equals(user.getConfirmPassword())) {
			errors.rejectValue("confirmPassword", "Match","les mots de passe doit etre les méme");
		}
		
		
	}

}
