package longop.mvvm;

import org.zkoss.bind.BindContext;
import org.zkoss.bind.Converter;
import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.lang.Threads;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zul.Label;
import org.zkoss.zul.ListModelList;

import bean.Person;
import bean.PersonDAO;

public class EchoVM {

	private ListModelList<Person> resultModel = new ListModelList<Person>();

	@Command
	public void startLongOperation(@BindingParam("comp") Component comp) {
		Clients.showBusy(comp, "Result coming in 3 seconds, please wait!");
		Events.echoEvent(new Event("onEcho", comp));
	}

	@Command
	public void doLongOperation(@BindingParam("comp") Component comp) {
		Threads.sleep(3000);
		resultModel.clear();
		resultModel.addAll(PersonDAO.getAll());
		Clients.clearBusy(comp);
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
}
