package org.zkoss.training.shadow;

import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.NotifyChange;

public class PermissionViewModel {
	
	public static final String TOGGLE_PERMISSION = "togglePermission";
	
	private static final String PERMITTED = "permitted";
	private boolean permitted = false;

	@Command(TOGGLE_PERMISSION)
	@NotifyChange(PERMITTED)
	public void togglePermission() {
		permitted = !permitted;
	}
	
	public boolean isPermitted() {
		return permitted;
	}
	
	public void setPermitted(boolean permitted) {
		this.permitted = permitted;
	}
}
