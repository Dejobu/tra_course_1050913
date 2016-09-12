package org.zkoss.training.mvvm;

import java.util.List;

import org.zkoss.zul.ListModelList;

public class CollectionBindingVM {

	/**
	 * Suggested using ZK's ListModel classes that supports rendering by difference instead of Java standard collection classes.
	 */
	private ListModelList<String> items;

	public CollectionBindingVM () {
		items = new ListModelList<String>();
		for (int i = 0; i < 10; i++) {
			items.add("item " + i);
		}
	}

}
