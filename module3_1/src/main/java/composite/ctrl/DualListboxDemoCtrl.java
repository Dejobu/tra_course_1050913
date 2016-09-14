/* DualListboxDemoCtrl.java

	Purpose:
		
	Description:
		
	History:
		2010/8/23, Created by Ian Tsai

Copyright (C) 2010 Potix Corporation. All Rights Reserved.
 */
package composite.ctrl;

import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Window;

import bean.PersonDAO;
import composite.DualListbox;
import composite.mvc.ChooseEvent;

/**
 * @author Ian Tsai
 *
 */
public class DualListboxDemoCtrl extends SelectorComposer<Window> {

	private static final long serialVersionUID = 1423762395723263226L;

	@Wire
	private DualListbox dualLBox;

	public void doAfterCompose(Window comp) throws Exception {
		super.doAfterCompose(comp);
		dualLBox.setModel(new ListModelList<Object>(PersonDAO.getAll()));
	}

	@Listen("onChoose = #dualLBox")
	public void choose(ChooseEvent event) {
		System.out.println("onChoose Event invoked: " + event.getData());
	}
}
