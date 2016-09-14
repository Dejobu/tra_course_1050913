package org.zkoss.training.debugging;

import java.util.LinkedList;
import java.util.List;

import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zul.ListModelList;

public class TabboxViewModel {

	private ListModelList<TabInfo> tabsModel = new ListModelList<TabInfo>();
	private List<TabInfo> list = new LinkedList<TabInfo>();
	private int index = 1;
	
	@Init
	public void init(){
		add();
	}
	
	/**
	 * Remove @NotifyChange to avoid re-rendering the whole tabbox
	 */
	@Command 
	@NotifyChange("list")
	public void add() {
		TabInfo tab = new TabInfo("tab " + index);
		tabsModel.add(tab);
		list.add(tab);
		index++;
	}

	public ListModelList<TabInfo> getTabsModel() {
		return tabsModel;
	}


	public List<TabInfo> getList() {
		return list;
	}
}
