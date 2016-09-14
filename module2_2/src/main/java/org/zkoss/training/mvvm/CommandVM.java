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
	
	@Command
	public void newOrder() {
		Clients.showNotification("new");
	}
	
	@Command("save")
	public void saveOrder() {
		Clients.showNotification("save " +selection);
	}

	@DefaultCommand
	public void defaultCommand() {
		Clients.showNotification("default command ");
	}

	@Command
	public void show(@BindingParam("myKey")String value){
		Clients.showNotification("passed "+value);
	}
	
	/**
	 * accessing a component will break MVVM principle
	 * @param component
	 */
	@Command
	public void showComponent(@BindingParam("component")Component component){
		Clients.showNotification("passed "+component.getClass().getSimpleName());
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
