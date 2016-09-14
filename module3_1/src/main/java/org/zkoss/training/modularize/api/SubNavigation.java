package org.zkoss.training.modularize.api;

import java.util.ArrayList;
import java.util.List;

public class SubNavigation extends NavigationElement {
	private static final String SUB_NAVIGATION = "sub-navigation";

	private List<NavigationElement> elements;

	public SubNavigation(String label) {
		this(label, null);
	}

	public SubNavigation(String label, String icon) {
		super(SUB_NAVIGATION, label, icon);
		elements = new ArrayList<NavigationElement>();
	}

	public List<NavigationElement> getElements() {
		return elements;
	}
	
	public int getCount() {
		return elements.size();
	}

	public void add(NavigationElement subElement) {
		elements.add(subElement);
	}
}
