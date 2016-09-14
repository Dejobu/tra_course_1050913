package org.zkoss.training.lifecycle;

import java.util.concurrent.atomic.AtomicInteger;

import org.zkoss.zk.ui.Desktop;
import org.zkoss.zk.ui.Execution;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.Event;

public class Log {
	private static final String LOG_COUNTER = "LOG_COUNTER";
	private static AtomicInteger globalCounter = new AtomicInteger(100000); 

	public static void logEvent(Event event) {
		printLog(event.getName() + " " + event.getTarget());
	}

	public static void logEvent(String message, Event event) {
		printLog(message + " " + event.getName() + " " + event.getTarget());
	}

	public static void log(String message, Object... args) {
		printLog(String.format(message, args));
	}

	public static void printLog(String message) {
		AtomicInteger counter = getCounter();
		System.out.println(Thread.currentThread().getName() + " " + counter.getAndIncrement() + ": " + message);
	}

	private static AtomicInteger getCounter() {
		Execution execution = Executions.getCurrent();
		if(execution == null) {
			return globalCounter;
		}
		Desktop desktop = execution.getDesktop();
		AtomicInteger desktopCounter = (AtomicInteger) desktop.getAttribute(LOG_COUNTER);
		if(desktopCounter == null) {
			desktopCounter = new AtomicInteger();
			desktop.setAttribute(LOG_COUNTER, desktopCounter);
		}
		return desktopCounter;
	}
}
