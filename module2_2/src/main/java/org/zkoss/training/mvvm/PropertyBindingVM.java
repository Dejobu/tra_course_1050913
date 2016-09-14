package org.zkoss.training.mvvm;

public class PropertyBindingVM {

	private String value = "default";

	public void setValue(String value) {
		this.value = value;
		System.out.println("save: " + value);
	}

	public String getValue() {
		System.out.println("read: " + value);
		return value;
	}
}
