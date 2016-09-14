package org.zkoss.training.hugedata;

import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listcell;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.ListitemRenderer;
import org.zkoss.zul.Window;

import bean.Person;

/**
 * 
 * @author ian
 * 
 */
public class HugeDataCtrl extends SelectorComposer<Window> {

	private static final long serialVersionUID = -7481683315099616293L;

	@Wire
	private Listbox personListbox;

	@Override
	public void doAfterCompose(Window comp) throws Exception {
		super.doAfterCompose(comp);
		personListbox.setItemRenderer(new ListitemRenderer<Person>() {
			public void render(Listitem lItem, Person person, int index) throws Exception {
				new Listcell(person.getFirstName()).setParent(lItem);
				new Listcell(person.getLastName()).setParent(lItem);
				new Listcell(person.isMarried() ? "O" : "X").setParent(lItem);
			}
		});
		PersonPagingListModel model2 = new PersonPagingListModel(personListbox.getPageSize());
		personListbox.setModel(model2);
	}

}
