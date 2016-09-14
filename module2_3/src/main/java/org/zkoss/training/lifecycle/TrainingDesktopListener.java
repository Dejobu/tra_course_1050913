package org.zkoss.training.lifecycle;

import javax.servlet.http.HttpServletRequest;

import org.zkoss.zk.ui.Desktop;
import org.zkoss.zk.ui.util.DesktopCleanup;
import org.zkoss.zk.ui.util.DesktopInit;

public class TrainingDesktopListener implements DesktopInit, DesktopCleanup {

	@Override
	public void init(Desktop desktop, Object request) throws Exception {
		HttpServletRequest servletRequest = (HttpServletRequest) request;
		Log.log("init desktop %s %s", desktop, servletRequest.getRequestURL().toString());
	}
	
	@Override
	public void cleanup(Desktop desktop) throws Exception {
		Log.log("cleanup desktop %s", desktop);
	}
}
