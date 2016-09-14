package longop.mvvm;

import org.zkoss.bind.BindContext;
import org.zkoss.bind.Converter;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.lang.Threads;
import org.zkoss.zul.Label;
import org.zkoss.zul.ListModelList;

import bean.Person;
import bean.PersonDAO;

public class LongVM {

	private ListModelList<Person> resultModel = new ListModelList<Person>();
	private int runningCount = 0;

	@Command @NotifyChange("runningCount")
	public void doLongOperation() {		
		Threads.sleep(3000);
		resultModel.clear();
		resultModel.addAll(PersonDAO.getAll());
		runningCount++;
	}

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

	public ListModelList<Person> getResultModel() {
		return resultModel;
	}

	public Integer getRunningCount() {
		return runningCount;
	}

	public void setRunningCount(int runningCount) {
		this.runningCount = runningCount;
	}
}
