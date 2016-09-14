package org.zkoss.training.lifecycle;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Desktop;
import org.zkoss.zk.ui.Page;
import org.zkoss.zk.ui.ShadowElement;
import org.zkoss.zk.ui.util.UiLifeCycle;

public class TrainingUiLifecycle implements UiLifeCycle {
	public void afterComponentAttached(Component comp, Page page) {
		Log.log("Component attached: %s", comp);
	}

	public void afterComponentDetached(Component comp, Page prevpage) {
		Log.log("Component detached: %s", comp);
	}

	public void afterComponentMoved(Component parent, Component child, Component prevparent) {
//		Log.log("Component moved:    %s from %s to %s", child, prevparent, child.getParent());
	}

	public void afterPageAttached(Page page, Desktop desktop) {
		Log.log("Page attached:      %s", page);
	}

	public void afterPageDetached(Page page, Desktop prevdesktop) {
		Log.log("Page detached:      %s", page);
	}

	public void afterShadowAttached(ShadowElement shadow, Component host) {
	}
	
	public void afterShadowDetached(ShadowElement shadow, Component prevhost) {
	}
}

