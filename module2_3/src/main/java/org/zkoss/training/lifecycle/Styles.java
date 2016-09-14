package org.zkoss.training.lifecycle;

import org.zkoss.zk.ui.Component;

public class Styles {
	public String getRedBorder(Component comp) {
		System.out.println("getRedBorder(%s) "+ comp);
		return "border: 10px solid red;";
	}
	public String getBlueBorder(Component comp) {
		System.out.println("getBlueBorder(%s) "+ comp);
		return "border: 10px solid blue;";
	}
	public String getGreenBorder(Component comp) {
		System.out.println("getGreenBorder(%s) "+ comp);
		return "border: 10px solid green;";
	}
}
