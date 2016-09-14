package org.zkoss.training.modularize.data;

import java.util.Map;

import org.zkoss.zk.ui.Page;
import org.zkoss.zk.ui.util.Initiator;

public class MainNavigationModelInit implements Initiator {

	private static final String MAIN_NAVIGATION_MODEL = "mainNavigationModel";
	
	@Override
	public void doInit(Page page, Map<String, Object> args) throws Exception {
		if(!page.getDesktop().hasAttribute(MAIN_NAVIGATION_MODEL)) {
			MainNavigationModel navModel = new MainNavigationModel();
			page.getDesktop().setAttribute(MAIN_NAVIGATION_MODEL, navModel);
		}
	}

}
