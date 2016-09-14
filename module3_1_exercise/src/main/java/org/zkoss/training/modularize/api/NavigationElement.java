package org.zkoss.training.modularize.api;

public class NavigationElement {
	private String type;
	private String label;
	private String icon;

	public NavigationElement(String type, String label, String icon) {
		super();
		this.type = type;
		this.label = label;
		this.icon = icon;
	}
	public String getLabel() {
		return label;
	}
	public String getIcon() {
		return icon;
	}
	public String getType() {
		return type;
	}
}
