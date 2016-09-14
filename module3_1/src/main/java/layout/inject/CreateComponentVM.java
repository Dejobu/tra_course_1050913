package layout.inject;

import java.util.HashMap;
import java.util.Map;

import org.zkoss.bind.annotation.Command;
import org.zkoss.zk.ui.Executions;

public class CreateComponentVM {

	@Command
	public void openLoginDialog() {
		Map<String, Object> args = new HashMap<String, Object>();
		args.put("username", "username");
		args.put("password", "password");
		Executions.createComponents("/layout-inject/create-component/loginDialog.zul", null, args);
	}
}
