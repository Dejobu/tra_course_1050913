package org.zkoss.training.lifecycle;

import org.zkoss.zk.ui.Desktop;
import org.zkoss.zk.ui.Execution;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.util.ExecutionMonitor;

public class TrainingExecutionMonitor implements ExecutionMonitor {

	@Override
	public void eventStart(Event event) {
		Log.logEvent("eventStart   ", event);
	}
	
	@Override
	public void eventComplete(Event event) {
		Log.logEvent("eventComplete", event);
	}
	
	@Override
	public void executionActivate(Execution exec, Desktop desktop) {
		Log.log("executionActivate   %s", exec);

	}

	@Override
	public void executionWait(Execution exec, Desktop desktop) {
		Log.log("executionWait       %s", exec);
	}

	@Override
	public void executionDeactivate(Execution exec, Desktop desktop) {
		Log.log("executionDeactivate %s", exec);
	}

	@Override
	public void executionAbort(Execution exec, Desktop desktop, Throwable t) {
		Log.log("executionAbort      %s", exec);
	}

	@Override
	public void desktopDestroy(Desktop desktop) {
		// TODO Auto-generated method stub

	}

	@Override
	public void eventSuspend(Event event) {
		// TODO Auto-generated method stub

	}

	@Override
	public void eventResume(Event event) {
		// TODO Auto-generated method stub

	}

}
