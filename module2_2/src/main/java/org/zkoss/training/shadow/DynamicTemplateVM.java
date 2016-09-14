package org.zkoss.training.shadow;

import java.util.Calendar;
import java.util.Date;

import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.NotifyChange;

public class DynamicTemplateVM {

	private Date today = Calendar.getInstance().getTime();
	private String value = "testing value";
	private boolean edit = false;
	
	@Command @NotifyChange("edit")
	public void toggle(){
		edit = !edit;
	}
	
	public Date getToday() {
		return today;
	}
	public void setToday(Date today) {
		this.today = today;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public boolean isEdit() {
		return edit;
	}
	public void setEdit(boolean edit) {
		this.edit = edit;
	}
	
}

