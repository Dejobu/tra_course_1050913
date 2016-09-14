package org.zkoss.training.mvvm;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import org.zkoss.bind.BindUtils;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Listen;

public class SenderComposer extends SelectorComposer {

	@Listen("onClick = #update")
	public void addProduct(Event fe) {
		//business logic
		
		Map<String, Object> arg = new HashMap<String, Object>();
		arg.put("item", "item at "+Calendar.getInstance().getTime());
		BindUtils.postGlobalCommand(null, null, "update", arg);
	}
}
