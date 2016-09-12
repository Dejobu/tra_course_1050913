package org.zkoss.training.mvvm;

import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.DefaultCommand;
import org.zkoss.bind.annotation.Init;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zul.ListModelList;

public class CommandVM {

	private String selection = null;
	private ListModelList<String> items = new ListModelList<String>();
	
	@Init
	public void init(){
		items.add("Matthew");
		items.add("Mark");
		items.add("Luke");
		items.add("John");
	}
	
	
	public String getSelection() {
		return selection;
	}

	public void setSelection(String selection) {
		this.selection = selection;
	}

	public ListModelList<String> getItems() {
		return items;
	}

	public void setItems(ListModelList<String> items) {
		this.items = items;
	}


}
