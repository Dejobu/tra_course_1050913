package org.zkoss.training.navigation;

import java.util.List;

import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.GlobalCommand;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.ScopeParam;
import org.zkoss.training.navigation.api.AbstractNavigationItem;
import org.zkoss.training.navigation.api.NavigationElement;
import org.zkoss.training.navigation.api.SubNavigation;
import org.zkoss.training.navigation.data.MainNavigationModel;
import org.zkoss.training.navigation.data.PageInfo;
import org.zkoss.zk.ui.Executions;

public class SidebarViewModel {

	public static final String BOOKMARK_CHANGED = "SidebarViewModel_bookmarkChanged";
	public static final String OPEN_SUB_NAVIGATION = "SidebarViewModel_openSubNavigation";
	public static final String SELECT_NAVIGATION_ITEM = "SidebarViewModel_selectNavigationItem";
	
	private MainNavigationModel navigationModel;
	
	@Init
	public void init(@ScopeParam(value="mainNavigationModel") MainNavigationModel navigationModel) {
		this.navigationModel = navigationModel;
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
	
	@GlobalCommand(BOOKMARK_CHANGED)
	public void bookmarkChanged(@BindingParam("bookmark") String bookmark) {
		navigationModel.gotoBookmark(bookmark);
	}
	
	private void navigateDirectly(AbstractNavigationItem<PageInfo> navigationItem) {
		Executions.getCurrent().getDesktop().setBookmark(navigationItem.getNavigationData().getBookmark());
		navigationModel.setActiveItem(navigationItem);
	}

	public MainNavigationModel getNavigationModel() {
		return navigationModel;
	}
}
