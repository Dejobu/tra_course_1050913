package org.zkoss.training.shop.service;

import java.util.Map;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Page;
import org.zkoss.zk.ui.util.Initiator;
import org.zkoss.zk.ui.util.InitiatorExt;

public class WorkbenchInit implements Initiator, InitiatorExt {

	public void doInit(Page page, Map<String, Object> args) throws Exception {
		if (!UserCredentialManager.getInstance().isAuthenticated()) {
			// You can also get Execution by desktop
			// page.getDesktop().getExecution().sendRedirect("login.zul");
			Executions.getCurrent().sendRedirect("login.zul");
		}
	}

	public void doAfterCompose(Page page, Component[] comps) throws Exception {
	}

	public boolean doCatch(Throwable ex) throws Exception {
		return false;
	}

	public void doFinally() throws Exception {
	}

}
