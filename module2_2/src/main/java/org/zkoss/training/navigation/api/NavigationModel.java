package org.zkoss.training.navigation.api;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public abstract class NavigationModel<T> {

	private Set<Listener<T>> listeners = new HashSet<Listener<T>>();
	
	private List<NavigationElement> elements;

	private AbstractNavigationItem<T> activeItem;

	public NavigationModel() {
		elements = new ArrayList<NavigationElement>();
	}	
	
	public List<NavigationElement> getElements() {
		return elements;
	}
	
	public AbstractNavigationItem<T> getActiveItem() {
		return activeItem;
	}

	public void setActiveItem(AbstractNavigationItem<T> activeItem) {
		AbstractNavigationItem<T> oldNavigationItem = getActiveItem();
		this.activeItem = activeItem;
		for (Listener listener : listeners){
			listener.onNavigationItemSelected(activeItem, oldNavigationItem);
		}
//		listeners.forEach(listener -> listener.onNavigationItemSelected(activeItem, oldNavigationItem)); java 8 syntax
	}
	
	public void addListener(Listener<T> listener) {
		listeners.add(listener);
	}
	
	public void removeListener(Listener<T> listener) {
		listeners.remove(listener);
	}
	
	public interface Listener<T> {
		public void onNavigationItemSelected(AbstractNavigationItem<T> newNavigationItem, AbstractNavigationItem<T> oldNavigationItem);
	}
	
	public abstract class NavigationItem extends AbstractNavigationItem<T> {
		public NavigationItem(String label, String icon) {
			super(label, icon);
		}

		@Override
		public boolean isActive() {
			return getActiveItem() == this;
		}
	}
}
