package org.zkoss.training.shop.controller;

import org.zkoss.bind.annotation.Command;
import org.zkoss.training.shop.service.UserCredentialManager;
import org.zkoss.zk.ui.Executions;

public class HeaderVM {

	@Command
	public void logoff(){
		UserCredentialManager.getInstance().logOff();
		Executions.sendRedirect("login.zul");
	}
}
