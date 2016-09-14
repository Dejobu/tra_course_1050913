package org.zkoss.training.modularize;

import org.zkoss.bind.annotation.Init;
import org.zkoss.zul.ListModelSet;

/**
 * Observer pattern also works on this scenario.
 * @author hawk
 *
 */
public class ParentViewModel implements CallBack {

	ListModelSet<String> chosenSet = new ListModelSet<String>();
	ChildViewModel childVm;
	
	@Init
	public void init(){
		childVm = new ChildViewModel();
		childVm.setCallback(this);
	}
	
	@Override
	public void notify(Object data) {
		chosenSet.add(data.toString());
	}

	public ListModelSet<String> getChosenList() {
		return chosenSet;
	}

	public ChildViewModel getChildVm() {
		return childVm;
	}

}
