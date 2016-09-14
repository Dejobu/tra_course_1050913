package tra.irts4.aaa000.ui.vm;

import org.zkoss.bind.annotation.Init;

public class A001 {
	private String message;
	private String account;
	private String password;
	
	@Init
	public void init() {
		this.message = "登入";
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
