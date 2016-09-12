package org.zkoss.training.shop.controller;

import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.training.shop.service.UserCredentialManager;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Session;

public class LoginVM {

	private String name;
	private String password;
	private String message;
	private boolean focus = true;
	private UserCredentialManager userManager;

	@Init
	public void init(@ContextParam(ContextType.SESSION) Session session) {
		userManager = UserCredentialManager.getInstance(session);
	}

	@Command
	public void login() {
		userManager.login(name, password);
		if (userManager.isAuthenticated()) {
			Executions.sendRedirect("index.zul");
		} else {
			message = "user name or password is invalid!";
		}
	}

	//TODO reset command
	
	@Command
	public void logoff(){
		UserCredentialManager.getInstance().logOff();
		Executions.sendRedirect("login.zul");
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getMessage() {
		return message;
	}

	public boolean isFocus() {
		return focus;
	}

}
