/* ValuedListitemRenderer.java

	Purpose:
		
	Description:
		
	History:
		2010/5/21, Created by Ian Tsai(Zanyking)

Copyright (C) 2010 Potix Corporation. All Rights Reserved.

{{IS_RIGHT
	This program is distributed under Apache Version 2.0 in the hope that
	it will be useful, but WITHOUT ANY WARRANTY.
}}IS_RIGHT
 */
package composite;

import org.zkoss.zul.Listhead;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.ListitemRenderer;

public abstract class DualListitemRenderer implements ListitemRenderer<Object> {

	public void render(Listitem item, Object data, int index) throws Exception {
		item.setValue(data);
		doRender(item, data);
	}

	public Listhead getListhead() {
		return null;
	}

	protected abstract void doRender(Listitem item, Object data) throws Exception;
}
