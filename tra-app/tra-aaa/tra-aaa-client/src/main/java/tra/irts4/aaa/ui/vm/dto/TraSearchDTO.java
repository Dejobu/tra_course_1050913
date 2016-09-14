package tra.irts4.aaa.ui.vm.dto;

import java.io.Serializable;
import java.util.Date;

public class TraSearchDTO implements Serializable {

	private static final long serialVersionUID = 7078855995618174171L;
	
	private Date startTime;
	private Date endTime;
	private String no;
	
	public Date getStartTime() {
		return startTime;
	}
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	public Date getEndTime() {
		return endTime;
	}
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	public String getNo() {
		return no;
	}
	public void setNo(String no) {
		if( no.equals("") )
			this.no = null;
		else
			this.no = no;
	}
}
