package org.zkoss.training.performance;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.util.Clients;

public class LongRunComposer extends SelectorComposer<Component>{

	
	@Listen("onClick = #submitButton")
	public void submit() throws InterruptedException{
		Thread.sleep(4000); //simulate a long business operation
		Clients.showNotification("Success");
	}
	
	@Listen("onClick = #submitButtonClientRun")
	public void submitClientRun() throws InterruptedException{
		Clients.showNotification("Success");
	}
}
