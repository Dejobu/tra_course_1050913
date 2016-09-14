package zkjsp;

import org.zkoss.bind.annotation.Command;
import org.zkoss.zul.Messagebox;

public class HelloMVVM {

	private String textboxStr = "Hello";
	private String labelStr = "MVVM";

	@Command
	public void sayHello() {
		Messagebox.show(textboxStr + " " + labelStr);
	}

	public String getTextboxStr() {
		return textboxStr;
	}

	public void setTextboxStr(String textboxStr) {
		this.textboxStr = textboxStr;
	}

	public String getLabelStr() {
		return labelStr;
	}

}
