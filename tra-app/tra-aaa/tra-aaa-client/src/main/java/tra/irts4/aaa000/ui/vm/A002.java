package tra.irts4.aaa000.ui.vm;

import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;

public class A002 {
	
	private String message;
	
	@Init
	public void init() {
		message = "Welcome!";
	}
	
	@Command
	@NotifyChange("message")
	public void changemsg() {
		if( message.indexOf("Welcome")!=-1 )
			message = "Good Bye~";
		else
			message = "Welcome Back!!!";
	}
	
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
}
