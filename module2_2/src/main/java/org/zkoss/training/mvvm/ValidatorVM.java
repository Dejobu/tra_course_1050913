package org.zkoss.training.mvvm;

import org.zkoss.bind.BindUtils;
import org.zkoss.bind.ValidationContext;
import org.zkoss.bind.Validator;
import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.sys.ValidationMessages;
import org.zkoss.bind.validator.AbstractValidator;
import org.zkoss.training.domain.Person;

public class ValidatorVM {
	private String email;
	private int price;
	private Person person = new Person();

	public Validator getPriceRangeValidator(){
		return new AbstractValidator() {

			@Override
			public void validate(ValidationContext ctx) {
				long min = (Long)ctx.getValidatorArg("min");
				long max = (Long)ctx.getValidatorArg("max");
				int price = (Integer)ctx.getProperty().getValue();
				if (price < min || price > max){
					addInvalidMessage(ctx, "price", "should between "+min+" and "+max);
				}
			}
		};
	}
	
	@Command
	public void save(){
		
	}
	

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}
}
