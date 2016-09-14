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

	public Converter<String, Boolean, Label> getMarriageStatusConverter() {
		return new Converter<String, Boolean, Label>() {
			public String coerceToUi(Boolean beanProp, Label component,
					BindContext ctx) {
				return beanProp ? "O" : "X";
			}
			public Boolean coerceToBean(String compAttr, Label component,
					BindContext ctx) {
				return component.getValue().equals("O");
			}
		};
	}

	@Command
	@NotifyChange("married")
	public void changeMarriageStatus() {
		married = !married;
	}

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
