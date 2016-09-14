package org.zkoss.training.mvvm;

import java.util.Calendar;

import org.zkoss.bind.GlobalCommandEvent;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zkmax.ui.select.annotation.Subscribe;
import org.zkoss.zul.Label;

public class ReceiverComposer extends SelectorComposer {

	@Wire
	private Label msg;
	
	@Subscribe("myqueue")
	public void refresh(Event evt) {
		if (evt instanceof GlobalCommandEvent) {
			if ("refresh".equals(((GlobalCommandEvent)evt).getCommand())) {
				msg.setValue("refreshed at "+ Calendar.getInstance().getTime());
			}
		}
	}
}
