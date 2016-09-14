package org.zkoss.training.mvvm;

import java.util.Calendar;
import java.util.Date;

import org.zkoss.bind.BindContext;
import org.zkoss.bind.Converter;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zul.Label;

public class ConverterVM {

	private Boolean married = false;
	private Date oneDay = Calendar.getInstance().getTime();


	public Boolean getMarried() {
		return married;
	}

	public Date getOneDay() {
		return oneDay;
	}

	public void setOneDay(Date oneDay) {
		this.oneDay = oneDay;
	}
}
