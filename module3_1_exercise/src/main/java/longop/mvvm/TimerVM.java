package longop.mvvm;

import java.util.Random;

import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.lang.Threads;
import org.zkoss.zk.ui.util.Clients;

public class TimerVM {

	private String _msg;
	private boolean _isRunning;

	public void setMsg(String msg) {
		_msg = msg;
	}

	public String getMsg() {
		return _msg;
	}

	public void setIsRunning(boolean isRunning) {
		_isRunning = isRunning;
	}

	public boolean getIsRunning() {
		return _isRunning;
	}

	@Command
	@NotifyChange({ "isRunning", "msg" })
	public void doLongSteps() {
		_isRunning = true;
		new Thread(new Runnable() {
			public void run() {
				int step = 1;
				Random r = new Random();
				while (step <= 9) {
					setMsg("Processing step " + step + "...");
					Threads.sleep(r.nextInt(2000) + 1000);
					step++;
				}
				setIsRunning(false);
			}
		}).start();
	}

	@Command
	@NotifyChange({ "isRunning", "msg" })
	public void checkStatus() {
		if (_isRunning) {
			Clients.showBusy(_msg);
		} else {
			_msg = "Processing done !";
			Clients.clearBusy();
		}
	}
}
