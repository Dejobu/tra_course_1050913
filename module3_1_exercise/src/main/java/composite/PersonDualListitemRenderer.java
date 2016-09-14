/* PersonDualListitemRenderer.java

	Purpose:
		
	Description:
		
	History:
		2010/8/23, Created by Ian Tsai

Copyright (C) 2010 Potix Corporation. All Rights Reserved.
*/
package composite;

import org.zkoss.zul.Listcell;
import org.zkoss.zul.Listhead;
import org.zkoss.zul.Listheader;
import org.zkoss.zul.Listitem;

import bean.Person;

public class PersonDualListitemRenderer extends DualListitemRenderer {

	public Listhead getListhead() {
		Listhead head = new Listhead();
		Listheader header;
		header = new Listheader();
		header.setHflex("min");
		header.setParent(head);
		
		header = new Listheader();
		header.setHflex("min");
		header.setParent(head);
		
		header = new Listheader();
		header.setHflex("1");
		header.setParent(head);
		
		header = new Listheader();
		header.setHflex("min");
		header.setParent(head);
		return head;
	}

	@Override
	protected void doRender(Listitem item, Object data) throws Exception {
		Person person = (Person) data;
		new Listcell(person.getFirstName()).setParent(item);
		new Listcell(person.getLastName()).setParent(item);
		new Listcell(person.getFullName()).setParent(item);
		new Listcell(person.isMarried() ? "O" : "X").setParent(item);
	}
}
