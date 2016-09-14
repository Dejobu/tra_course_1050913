package org.zkoss.training.modularize;

import java.util.List;

import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.ScopeParam;
import org.zkoss.training.modularize.api.AbstractNavigationItem;
import org.zkoss.training.modularize.api.NavigationElement;
import org.zkoss.training.modularize.api.SubNavigation;
import org.zkoss.training.modularize.data.MainNavigationModel;
import org.zkoss.training.modularize.data.PageInfo;

public class SidebarViewModel {

	public static final String OPEN_SUB_NAVIGATION = "SidebarViewModel_openSubNavigation";
	public static final String SELECT_NAVIGATION_ITEM = "SidebarViewModel_selectNavigationItem";
	
	private MainNavigationModel navigationModel;
	
	@Init //TODO use @ScopeParam to receive  MainNavigationModel
	public void init() {
		//this.navigationModel = navigationModel;
	}

	@Command(SELECT_NAVIGATION_ITEM) 
	public void selectNavigationItem(@BindingParam("navigationItem") AbstractNavigationItem<PageInfo> navigationItem) {
		navigateDirectly(navigationItem);
	}
	
	@Command(OPEN_SUB_NAVIGATION)
	public void openSubNavigation(@BindingParam("subNavigation") SubNavigation subNavigation, @BindingParam("open") boolean open) {
		if(open) {
			List<NavigationElement> subElements = subNavigation.getElements();
			AbstractNavigationItem<PageInfo> oldActiveItem = navigationModel.getActiveItem();
			if(!subElements.contains(oldActiveItem)) { //don't navigate if a sub element is already open, simplified case not caring for nested subNavigations
				@SuppressWarnings("unchecked")
				AbstractNavigationItem<PageInfo> activeItem = (AbstractNavigationItem<PageInfo>) subElements.get(0);
				navigateDirectly(activeItem);
			}
		}
	}
	
	private void navigateDirectly(AbstractNavigationItem<PageInfo> navigationItem) {
		navigationModel.setActiveItem(navigationItem);
	}

	public MainNavigationModel getNavigationModel() {
		return navigationModel;
	}
}
