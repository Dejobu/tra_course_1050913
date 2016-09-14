package org.zkoss.training.debugging;

import java.util.Calendar;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Label;
import org.zkoss.zul.Timer;

public class TimerComposer extends SelectorComposer<Component> {
	@Wire
	Timer refreshTimer;
	@Wire
	Label message;
	
	@Override
	public void doAfterCompose(Component comp) throws Exception {
		super.doAfterCompose(comp);
		refreshTimer.start(); //unnecessary
		System.out.println("doAfterCompose");
	}

	@Listen("onTimer = #refreshTimer")
	public void refreshStats() {
		message.setValue("refreshStats called at "+Calendar.getInstance().getTime().toString());
	}
}
