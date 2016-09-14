package tra.irts4.aaa000.ui.vm;

import java.util.ArrayList;
import java.util.List;

import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.NotifyChange;

public class A003 {

	private String info;
	private List<String> interest = new ArrayList<String>();
	
	@Command
	@NotifyChange("info")
	public void getInfo(@BindingParam("checkMark") boolean checkMark, @BindingParam("method") String method) {
		if (checkMark) {
			info = "NotifyChange: "+method;
		}
	}
	
	@Command
	@NotifyChange("interest")
	public void getInsterest(@BindingParam("checkMark") boolean checkMark, @BindingParam("type") String type) {
		if (checkMark) {
			interest.add(type);
		} else {
			interest.remove(type);			
		}
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	public List<String> getInterest() {
		return interest;
	}

	public void setInterest(List<String> interest) {
		this.interest = interest;
	}
}
