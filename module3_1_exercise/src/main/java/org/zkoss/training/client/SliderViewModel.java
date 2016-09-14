package org.zkoss.training.client;

import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.bind.annotation.ToServerCommand;

@ToServerCommand("changeValue")
public class SliderViewModel {

	private Integer value = 0;
	
	@Command @NotifyChange("value")
	public void changeValue(@BindingParam("value")String value){
		this.value = Integer.parseInt(value);
	}

	public Integer getValue() {
		return value;
	}

	public void setValue(Integer value) {
		this.value = value;
	}
	
}
