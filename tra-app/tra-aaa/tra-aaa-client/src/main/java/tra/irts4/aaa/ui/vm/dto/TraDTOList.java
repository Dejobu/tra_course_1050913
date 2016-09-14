package tra.irts4.aaa.ui.vm.dto;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.zkoss.bind.BindContext;
import org.zkoss.bind.Converter;
import org.zkoss.zul.Label;

public class TraDTOList {
	
	private List<Train> trainModel = new ArrayList<Train>();
	private List<String> trainNoModel = new ArrayList<>();
	private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	public TraDTOList() {
		String pattern = "yyyy/MM/dd HH:mm:ss";

		try {
			trainModel.add(new Train("101", "自強", new SimpleDateFormat(pattern).parse("2016/08/10 07:39:00"), new SimpleDateFormat(pattern).parse("2016/08/10 12:44:00"), "台北--高雄", "山線", "順行", false, "1234567"));
			trainModel.add(new Train("111", "莒光", new SimpleDateFormat(pattern).parse("2016/08/11 07:57:00"), new SimpleDateFormat(pattern).parse("2016/08/11 10:11:00"), "台北--台南", "山線", "順行", false, "45"));
			trainModel.add(new Train("124", "莒光", new SimpleDateFormat(pattern).parse("2016/08/11 23:15:00"), new SimpleDateFormat(pattern).parse("2016/08/12 01:57:00"), "台北--台中", "山線", "順行", true, "3456"));
			trainModel.add(new Train("127", "區間", new SimpleDateFormat(pattern).parse("2016/08/12 09:11:00"), new SimpleDateFormat(pattern).parse("2016/08/12 14:11:00"), "台北--新竹", "山線", "順行", false, "2345"));
			trainModel.add(new Train("158", "自強", new SimpleDateFormat(pattern).parse("2016/08/12 09:26:00"), new SimpleDateFormat(pattern).parse("2016/08/12 13:40:00"), "台北--嘉義", "海線", "順行", false, "23"));
			trainModel.add(new Train("2101", "普悠瑪", new SimpleDateFormat(pattern).parse("2016/08/13 10:30:00"), new SimpleDateFormat(pattern).parse("2016/08/13 13:00:00"), "台北--花蓮", "海線", "順行", false, "1234"));
			
			trainNoModel = Arrays.asList(new String[]{"101", "111", "124", "127", "158", "2101"});
			
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}

	public List<Train> getTrainModel() {
		return trainModel;
	}

	public List<String> getTrainNoModel() {
		return trainNoModel;
	}
	
	public Converter<String, Boolean, Label> getCrossDayConvert() {
		return new Converter<String, Boolean, Label>() {
			public String coerceToUi(Boolean beanProp, Label component, BindContext ctx) {
				return beanProp ? "是" : "否";
			}
			public Boolean coerceToBean(String compAttr, Label component, BindContext ctx) {
				return component.getValue().equals("是");
			}
		};
	}
	
	public Converter<String, Date, Label> getDateFormatConvert() {
		return new Converter<String, Date, Label>() {
			public String coerceToUi(Date date, Label component, BindContext ctx) {
		   		String str = "";
		   		if (date != null) {
		   			str = sdf.format(date);
	    		}
				return str;
			}

			public Date coerceToBean(String date, Label component, BindContext ctx) {
		        try {
					return date == null ? null : sdf.parse(date);
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					return null;
				}
			}
		};
	}
}
