/* ChooseEvent.java

	Purpose:
		
	Description:
		
	History:
		2010/9/30, Created by Ian Tsai

Copyright (C) 2010 Potix Corporation. All Rights Reserved.
 */
package composite.mvc;

import java.util.Set;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zul.Listitem;

/**
 * @author Ian Tsai
 *
 */
public class ChooseEvent extends Event {

	private static final long serialVersionUID = 9034883432669619262L;

	/**
	 * 
	 * @param target
	 * @param data
	 */
	public ChooseEvent(Component target, Set<Listitem> data) {
		super("onChoose", target, data);
	}

	@SuppressWarnings("unchecked")
	public Set<Listitem> getChosenData() {
		return (Set<Listitem>) getData();
	}

}
