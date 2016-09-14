package org.zkoss.training.mvvm.globalcommand;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.zkoss.bind.BindUtils;
import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.GlobalCommand;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zul.ListModelList;

public class ListViewModel {

	private List<String> items ; 
	private Date lastUpdate;
	private boolean visible = true;
	
	@Init
	public void init(){
		items = new ListModelList<String>();
		
	}
	@GlobalCommand @NotifyChange("lastUpdate")
	public void refresh(@BindingParam("item")String item){
		items.add(item);
		lastUpdate = Calendar.getInstance().getTime(); 
		BindUtils.postGlobalCommand(null, null, "reset", null);
	}

	@GlobalCommand @NotifyChange("visible")
	public void show(){
		visible = true;
	}
	@GlobalCommand @NotifyChange("visible")
	public void hide(){
		visible =false;
	}
	
	public List<String> getItems() {
		return items;
	}

	public void setItems(List<String> items) {
		this.items = items;
	}

	public Date getLastUpdate() {
		return lastUpdate;
	}

	public void setLastUpdate(Date lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

	public boolean isVisible() {
		return visible;
	}

	public void setVisible(boolean visible) {
		this.visible = visible;
	}
}
