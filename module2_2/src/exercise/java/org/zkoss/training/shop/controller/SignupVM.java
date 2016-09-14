package org.zkoss.training.shop.controller;

import org.zkoss.bind.ValidationContext;
import org.zkoss.bind.Validator;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.bind.validator.AbstractValidator;
import org.zkoss.training.shop.model.bean.User;
import org.zkoss.training.shop.service.UserCredentialManager;
import org.zkoss.zk.ui.util.Clients;


public class SignupVM {

	public static final String ERR_MSG_KEY = "signup";
	private User user = new User(1L, "", "", "");
	private String confirmedPassword = "";
	
	//TODO submit
	
	//TODO reset
	
	public Validator getPasswordValidator(){
		return new AbstractValidator() {

			@Override
			public void validate(ValidationContext ctx) {
				if (!confirmedPassword.equals(ctx.getProperties("password")[0].getValue())){
					addInvalidMessage(ctx, ERR_MSG_KEY, "2 password fields are not equal");
				}
			}
		};
	}
	
	public Validator getNameConflictValidator(){
		return new AbstractValidator() {
			
			@Override
			public void validate(ValidationContext ctx) {
				if (UserCredentialManager.getInstance().find(ctx.getProperty().getValue().toString())!=null){
					addInvalidMessage(ctx, ERR_MSG_KEY, "User name already exists");
				}
			}
		};
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getConfirmedPassword() {
		return confirmedPassword;
	}

	public void setConfirmedPassword(String confirmedPassword) {
		this.confirmedPassword = confirmedPassword;
	}
}
