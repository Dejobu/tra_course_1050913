package org.zkoss.training.lifecycle;

import java.util.List;

import org.zkoss.zk.ui.Execution;
import org.zkoss.zk.ui.util.ExecutionCleanup;
import org.zkoss.zk.ui.util.ExecutionInit;

public class TrainingExecutionListener implements ExecutionInit, ExecutionCleanup {
	
	@Override
	public void init(Execution exec, Execution parent) throws Exception {
		boolean asyncUpdate = exec.isAsyncUpdate(exec.getDesktop().getFirstPage());
		Log.log("init execution %s %s %s", exec, parent, asyncUpdate ? "--- AJAX ---" : "--- PAGE ---");
	}

	@Override
	public void cleanup(Execution exec, Execution parent, List<Throwable> errs)
			throws Exception {
		Log.log("cleanup execution %s %s", exec, parent);
	}
}
