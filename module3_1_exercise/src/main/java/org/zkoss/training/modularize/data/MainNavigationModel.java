package org.zkoss.training.modularize.data;

import java.util.ArrayList;
import java.util.List;

import org.zkoss.bind.BindUtils;
import org.zkoss.training.modularize.api.AbstractNavigationItem;
import org.zkoss.training.modularize.api.NavigationElement;
import org.zkoss.training.modularize.api.SubNavigation;

public class MainNavigationModel {

	
	private List<NavigationElement> elements;

	private AbstractNavigationItem<PageInfo> activeItem;

	public MainNavigationModel() {
		elements = new ArrayList<NavigationElement>();
		
		NavigationItem homeItem = createNavigationItem("Home", "z-icon-home", "pages/home.zul");
		elements.add(homeItem);
		elements.add(createNavigationItem("Users", "z-icon-users", "pages/dummy.zul"));
		elements.add(createNavigationItem("Catalog", "z-icon-list", "pages/dummy.zul"));
		elements.add(createContactSubNavigation());
		elements.add(createNavigationItem("Administrators", "z-icon-gear", "pages/dummy.zul"));
		
		setActiveItem(homeItem); 
	}

	private SubNavigation createContactSubNavigation() {
		SubNavigation contactSubNavigation = new SubNavigation("Contact", "z-icon-phone");
		contactSubNavigation.add(createNavigationItem("Support", null, "pages/dummy.zul"));
		contactSubNavigation.add(createNavigationItem("Sales", null, "pages/dummy.zul"));
		return contactSubNavigation;
	}
	
	
	NavigationItem createNavigationItem(final String label, String icon, final String path) {
		NavigationItem navigationItem = new NavigationItem(label, icon) {
			@Override
			public PageInfo getNavigationData() {
				return new PageInfo(path, label);
			}
		};
		return navigationItem;
	}	
	
	public List<NavigationElement> getElements() {
		return elements;
	}
	
	public AbstractNavigationItem<PageInfo> getActiveItem() {
		return activeItem;
	}

	public void setActiveItem(AbstractNavigationItem<PageInfo> activeItem) {
		AbstractNavigationItem<PageInfo> oldNavigationItem = getActiveItem();
		this.activeItem = activeItem;
		BindUtils.postNotifyChange(null, null, this, "activeItem");
		BindUtils.postNotifyChange(null, null, activeItem, "active");
		BindUtils.postNotifyChange(null, null, oldNavigationItem, "active");
	}
	
	public abstract class NavigationItem extends AbstractNavigationItem<PageInfo> {
		public NavigationItem(String label, String icon) {
			super(label, icon);
		}

		@Override
		public boolean isActive() {
			return getActiveItem() == this;
		}
	}
}
