package org.zkoss.training.mvvm;

import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.GlobalCommand;
import org.zkoss.bind.annotation.NotifyChange;

public class ReceiverVM {
	private String value;
	
	@GlobalCommand
	@NotifyChange("value")
	public void update(@BindingParam("item")String item) {
		value = item;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
}
