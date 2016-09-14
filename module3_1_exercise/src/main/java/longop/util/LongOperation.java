package longop.util;

import java.util.UUID;
import java.util.concurrent.atomic.AtomicBoolean;

import org.zkoss.zk.ui.Desktop;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zk.ui.WebApps;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.EventQueue;
import org.zkoss.zk.ui.event.EventQueues;
import org.zkoss.zk.ui.sys.DesktopCache;
import org.zkoss.zk.ui.sys.WebAppCtrl;

public abstract class LongOperation {

	private boolean _abortable;
	private AtomicBoolean _aborted;
	private String _desktopId;
	private DesktopCache _desktopCache;

	private static final String WORKING_QUEUE_NAME = "workingQueue" + UUID.randomUUID();

	public LongOperation() {
	}

	public LongOperation(boolean abortable) {
		_abortable = abortable;
		if (abortable)
			_aborted = new AtomicBoolean();
	}

	public void abort() {
		if (_abortable)
			throw new IllegalStateException("Long operation is not abortable");

		_aborted.set(true);
	}

	/**
	 * Execute long operation here
	 * 
	 * @param input
	 *            pass any arguments required for the long operation
	 * @return result of the long operation
	 */
	public abstract void doLongOperation();

	/**
	 * Triggered when long operation finished.
	 * @param result
	 */
	public abstract void onFinish();

	public void start() {
		_desktopId = Executions.getCurrent().getDesktop().getId();
		_desktopCache = ((WebAppCtrl) WebApps.getCurrent()).getDesktopCache(Sessions.getCurrent());
		final EventQueue<Event> eventQueue = EventQueues.lookup(WORKING_QUEUE_NAME);

		eventQueue.subscribe(new EventListener<Event>() {
			public void onEvent(Event event) throws Exception {
				doLongOperation();
			}
		}, new EventListener<Event>() {
			public void onEvent(Event event) throws Exception {
				finish();
			}
		});

		eventQueue.publish(new Event("start"));
	}

	/**
	 * Update UI during long operation
	 * @param callback
	 */
	public final void updateUI(LongOperationCallback callback) {
		try {
			Desktop desktop = getDesktop();
			Executions.activate(desktop);
			callback.execute();
			Executions.deactivate(desktop);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/*
	 * cleanup long operation
	 */
	private void finish() {
		onFinish();
		EventQueues.remove(WORKING_QUEUE_NAME);
	}

	public boolean isAbortable() {
		return _abortable;
	}

	public boolean isAborted() {
		if (!_abortable)
			throw new IllegalStateException("Long operation is not abortable");

		return _aborted.get();
	}

	private Desktop getDesktop() {
		return _desktopCache.getDesktopIfAny(_desktopId);
	}

	public interface LongOperationCallback {
		public void execute();
	}

}
