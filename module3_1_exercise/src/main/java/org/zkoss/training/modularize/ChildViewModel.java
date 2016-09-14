package org.zkoss.training.modularize;

import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.zul.ListModelList;

public class ChildViewModel {

	ListModelList<String> itemList = new ListModelList<String>();
	CallBack callBack = new CallBack() { //null object pattern
		@Override
		public void notify(Object data) {
		}
	};
	
	public ChildViewModel() {
		for (int i = 0 ; i < 200 ; i++){
			itemList.add("item "+i);
		}
	}

	@Command
	public void report(@BindingParam("item") String item){
		//TODO call back
	}
	
	public void setCallback(CallBack callBack){
		this.callBack = callBack;
	}
	
	public ListModelList<String> getItemList() {
		return itemList;
	}
	
	
}
