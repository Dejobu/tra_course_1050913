package org.zkoss.training.navigation.data;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.zkoss.bind.BindUtils;
import org.zkoss.training.navigation.api.AbstractNavigationItem;
import org.zkoss.training.navigation.api.NavigationElement;
import org.zkoss.training.navigation.api.NavigationModel;
import org.zkoss.training.navigation.api.SubNavigation;

public class MainNavigationModel extends NavigationModel<PageInfo>{

	Map<String, NavigationItem> bookmarkPageInfos = new HashMap<String, NavigationItem>(); 
	
	public void init() {
		List<NavigationElement> elements = getElements();
		
		NavigationItem homeItem = createNavigationItem("Home", "z-icon-home", "/WEB-INF/zul/nav/pages/home.zul");
		elements.add(homeItem);
		elements.add(createNavigationItem("Users", "z-icon-users", "/WEB-INF/zul/nav/pages/dummy.zul"));
		elements.add(createNavigationItem("Catalog", "z-icon-list", "/WEB-INF/zul/nav/pages/dummy.zul"));
		elements.add(createContactSubNavigation());
		elements.add(createNavigationItem("Administrators", "z-icon-gear", "/WEB-INF/zul/nav/pages/dummy.zul"));

		setActiveItem(homeItem); 

		addNavigationListener();
	}

	private SubNavigation createContactSubNavigation() {
		SubNavigation contactSubNavigation = new SubNavigation("Contact", "z-icon-phone");
		contactSubNavigation.add(createNavigationItem("Support", null, "/WEB-INF/zul/nav/pages/dummy.zul"));
		contactSubNavigation.add(createNavigationItem("Sales", null, "/WEB-INF/zul/nav/pages/dummy.zul"));
		return contactSubNavigation;
	}
	
	private void addNavigationListener() {
		this.addListener(new Listener() {
			@Override
			public void onNavigationItemSelected(
					AbstractNavigationItem newNavigationItem,
					AbstractNavigationItem oldNavigationItem) {
				BindUtils.postNotifyChange(null, null, MainNavigationModel.this, "activeItem");
				BindUtils.postNotifyChange(null, null, newNavigationItem, "active");
				BindUtils.postNotifyChange(null, null, oldNavigationItem, "active");
				
			}
			
		});
//		java 8 syntax
//		this.addListener((newNavigationItem, oldNavigationItem) -> {
//				BindUtils.postNotifyChange(null, null, MainNavigationModel.this, "activeItem");
//				BindUtils.postNotifyChange(null, null, newNavigationItem, "active");
//				BindUtils.postNotifyChange(null, null, oldNavigationItem, "active");
//			}
//		);
	}
	
	NavigationItem createNavigationItem(final String label, String icon, final String path) {
		NavigationItem navigationItem = new NavigationItem(label, icon) {
			@Override
			public PageInfo getNavigationData() {
				return new PageInfo(path, label);
			}
		};
		bookmarkPageInfos.put(label, navigationItem);
		return navigationItem;
	}
	
	public void gotoBookmark(String bookmark) {
		setActiveItem(bookmarkPageInfos.get(bookmark));
	}
}
