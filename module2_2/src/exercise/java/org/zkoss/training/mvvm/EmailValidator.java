package org.zkoss.training.mvvm;

import java.util.regex.Pattern;

import org.zkoss.bind.ValidationContext;
import org.zkoss.bind.validator.AbstractValidator;

public class EmailValidator extends AbstractValidator {

	private static Pattern pattern = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,}", Pattern.CASE_INSENSITIVE);
	@Override
	public void validate(ValidationContext ctx) {
	}

}
