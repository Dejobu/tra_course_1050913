package org.zkoss.training.navigation.api;

public abstract class AbstractNavigationItem<T> extends NavigationElement {
	private static final String NAVIGATION_ITEM = "navigation-item";

	public AbstractNavigationItem(String label, String icon) {
		super(NAVIGATION_ITEM, label, icon);
	}

	public abstract boolean isActive();

	public abstract T getNavigationData();
}
