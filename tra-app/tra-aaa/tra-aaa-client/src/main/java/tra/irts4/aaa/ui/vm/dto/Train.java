package tra.irts4.aaa.ui.vm.dto;

import java.util.Date;

public class Train {
	
	private String no;
	private String type;
	private Date startTime;
	private Date endTime;
	private String originDest;
	private String line;
	private String travelWay;
	private boolean crossDay;
	private String transferMethod;
	
	public Train() {}
	
	public Train(String no, String type, Date startTime, Date endTime, String originDest, String line,
				String travelWay, boolean crossDay, String transferMethod) {
		super();
		this.no = no;
		this.type = type;
		this.startTime = startTime;
		this.endTime = endTime;
		this.originDest = originDest;
		this.line = line;
		this.travelWay = travelWay;
		this.crossDay = crossDay;
		this.transferMethod = transferMethod;
	}
	
	public String getNo() {
		return no;
	}

	public void setNo(String no) {
		this.no = no;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

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

	public String getOriginDest() {
		return originDest;
	}

	public void setOriginDest(String originDest) {
		this.originDest = originDest;
	}

	public String getLine() {
		return line;
	}

	public void setLine(String line) {
		this.line = line;
	}

	public String getTravelWay() {
		return travelWay;
	}

	public void setTravelWay(String travelWay) {
		this.travelWay = travelWay;
	}

	public boolean isCrossDay() {
		return crossDay;
	}

	public void setCrossDay(boolean crossDay) {
		this.crossDay = crossDay;
	}

	public String getTransferMethod() {
		return transferMethod;
	}

	public void setTransferMethod(String transferMethod) {
		this.transferMethod = transferMethod;
	}

}
