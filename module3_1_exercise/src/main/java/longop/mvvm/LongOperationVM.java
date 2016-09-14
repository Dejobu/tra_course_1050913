package longop.mvvm;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import longop.util.LongOperation;

import org.zkoss.bind.BindContext;
import org.zkoss.bind.BindUtils;
import org.zkoss.bind.Converter;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.lang.Threads;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zul.Label;
import org.zkoss.zul.ListModelList;

import bean.Person;
import bean.PersonDAO;

public class LongOperationVM {

	private ListModelList<Person> resultModel = new ListModelList<Person>();
	private boolean progressVisible;
	private String progressStatus;
	private int progressPercent;

	@Command
	public void startLongOperation() {
		LongOperation longOperation = new LongOperation() {
			private List<Person> result = null;
			public void doLongOperation() {
				//simulate load data from DB
				//dataService.findById(id);
				Threads.sleep(3000);
				result = PersonDAO.getAll();
			}

			public void onFinish() {
				Clients.clearBusy();
				resultModel.addAll(result);
			}
		};
		Clients.showBusy("Result coming in 3 seconds, please wait!");
		longOperation.start();
	}

	@Command
	@NotifyChange("progressVisible")
	public void startLongOperationStep() {
		final Object bean = this;
		LongOperation longOperation = new LongOperation() {
			Random r = new Random(System.currentTimeMillis());
			List<Person> list = new ArrayList<Person>();
			public void doLongOperation() {
				//simulate load data with 3 steps
				step("Fetching Data ...");
				step("Filtering Data ...");
				step("Sorting Data ...");
				step("Finish");
			}

			LongOperationCallback updateStatus = new LongOperationCallback() {
				public void execute() {
					BindUtils.postNotifyChange(null, null, bean, "progressStatus");
				}
			};
			LongOperationCallback updatePercent = new LongOperationCallback() {
				public void execute() {
					BindUtils.postNotifyChange(null, null, bean, "progressPercent");
				}
			};
			private void step(final String message) {
				String m = message.toLowerCase();
				progressStatus = message;
				if (m.contains("fetch")) { // fetching
					updateUI(updateStatus);
					Threads.sleep(r.nextInt(500) + 1500);
					list.clear();
					list.addAll(PersonDAO.getAll());
					progressPercent = 33;
					updateUI(updatePercent);
				} else if (m.contains("filter")) { // filtering
					updateUI(updateStatus);
					Threads.sleep(r.nextInt(500) + 1500);
					Iterator<Person> it = list.iterator();
					while (it.hasNext()) {
						Person p = it.next();
						if (!p.isMarried())
							it.remove();
					}
					progressPercent = 67;
					updateUI(updatePercent);
				} else if (m.contains("sort")) { // sorting
					updateUI(updateStatus);
					Threads.sleep(r.nextInt(500) + 1500);
					Collections.sort(list, new Comparator<Person>() {
						public int compare(Person p1, Person p2) {
							return p1.getFullName().compareTo(p2.getFullName());
						}
					});
					progressPercent = 100;
					updateUI(updatePercent);
				} else { // finish
					Threads.sleep(500);
				}
			}

			public void onFinish() {
				progressVisible = false;
				progressPercent = 0;
				BindUtils.postNotifyChange(null, null, bean, "progressPercent");
				BindUtils.postNotifyChange(null, null, bean, "progressVisible");
				resultModel.clear();
				resultModel.addAll(list);
			}
		};
		progressVisible = true;
		longOperation.start();
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

	public boolean isProgressVisible() {
		return progressVisible;
	}

	public String getProgressStatus() {
		return progressStatus;
	}

	public int getProgressPercent() {
		return progressPercent;
	}
}
