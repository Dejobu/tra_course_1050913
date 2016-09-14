/*
 * DualListbox.java 
 * 		Purpose: 
 * 		Description: 
 * 		History: 2010/5/20, Created by Ian Tsai(Zanyking) 
 * 
 * Copyright (C) 2010 Potix
 * Corporation. All Rights Reserved. 
 * {{IS_RIGHT This program is distributed under Apache Version 2.0 in the hope that it will be useful, but WITHOUT ANY WARRANTY. }}IS_RIGHT
 */
package composite;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zul.Div;
import org.zkoss.zul.Hlayout;
import org.zkoss.zul.Image;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listhead;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.Vlayout;

/**
 * This DualListbox require a small size of ListModel(depend on system and
 * concurrent usage to the whole system) for it's candidate Listbox.<br>
 * User can choose some listitem in candidate box, and move it to chosen box.
 */
public class DualListbox extends Div {

	private static final long serialVersionUID = 6803732119108625425L;

	private static final String DEFAULT_WIDTH = "300px";

	private static final String DEFAULT_HEIGHT = "300px";

	private Listbox candidateLb;
	private Listbox chosenLb;
	private ListModelList<Object> candidateModel;
	private ListModelList<Object> chosenDataModel;
	private Vlayout orderBtnVbox;

	/**
	 * a default constructor for any other purpose.
	 */
	public DualListbox() {
		Hlayout box = new Hlayout();
		box.setParent(this);
		Listbox leftList = new Listbox();
		leftList.setRows(8);
		setCandidateLb(leftList);
		getCandidateLb().setWidth(DEFAULT_WIDTH);
		getCandidateLb().setParent(box);

		initChoicesBtns().setParent(box);
		Listbox rightList = new Listbox();
		rightList.setRows(8);
		setChosenLb(rightList);
		getChosenLb().setWidth(DEFAULT_WIDTH);
		getChosenLb().setParent(box);
		getChosenLb().setModel(setChosenDataModel(new ListModelList<Object>()));

		orderBtnVbox = initOrderBtns();
		orderBtnVbox.setParent(box);

		this.setHeight(DEFAULT_HEIGHT);
	}

	public void setChooseAllVisible(boolean visible) {
		chooseAll.setVisible(visible);
	}

	private Image chooseAll;

	private Image choose;

	private Image remove;

	private Image removeAll;

	private Vlayout initChoicesBtns() {
		Vlayout v = new Vlayout();
		chooseAll = new Image("/images/rightrightarrow_g.jpg");
		chooseAll.addEventListener(Events.ON_CLICK, new EventListener<Event>() {
			public void onEvent(Event event) throws Exception {
				chooseAll();
			}
		});
		choose = new Image("/images/rightarrow_g.jpg");
		choose.addEventListener(Events.ON_CLICK, new EventListener<Event>() {
			public void onEvent(Event event) throws Exception {
				choose();
			}
		});
		remove = new Image("/images/leftarrow_g.jpg");
		remove.addEventListener(Events.ON_CLICK, new EventListener<Event>() {
			public void onEvent(Event event) throws Exception {
				remove();
			}
		});
		removeAll = new Image("/images/leftleftarrow_g.jpg");
		removeAll.addEventListener(Events.ON_CLICK, new EventListener<Event>() {
			public void onEvent(Event event) throws Exception {
				removeAll();
			}
		});
		chooseAll.setStyle("cursor: pointer;");
		choose.setStyle("cursor: pointer;");
		remove.setStyle("cursor: pointer;");
		removeAll.setStyle("cursor: pointer;");
		chooseAll.setParent(v);
		choose.setParent(v);
		remove.setParent(v);
		removeAll.setParent(v);
		return v;
	}

	public void chooseAll() {
		getChosenDataModel().addAll(getCandidateModel());
		getCandidateModel().clear();
	}

	public void choose() {
		Set<Listitem> set = getCandidateLb().getSelectedItems();

		for (Object obj : new ArrayList<Listitem>(set)) {
			obj = ((Listitem) obj).getValue();
			getChosenDataModel().add(obj);
			getCandidateModel().remove(obj);
		}
	}

	public void remove() {
		Set<Listitem> set = getChosenLb().getSelectedItems();

		for (Object obj : new ArrayList<Listitem>(set)) {
			obj = ((Listitem) obj).getValue();
			getCandidateModel().add(obj);
			getChosenDataModel().remove(obj);
		}
	}

	public void removeAll() {
		getCandidateModel().addAll(getChosenDataModel());
		getChosenDataModel().clear();
	}

	private Image top;

	private Image up;

	private Image down;

	private Image bottom;

	private Vlayout initOrderBtns() {
		Vlayout v = new Vlayout();
		top = new Image("/images/upuparrow_g.jpg");
		top.addEventListener(Events.ON_CLICK, new EventListener<Event>() {
			public void onEvent(Event event) throws Exception {
				top();
			}
		});
		up = new Image("/images/uparrow_g.jpg");
		up.addEventListener(Events.ON_CLICK, new EventListener<Event>() {
			public void onEvent(Event event) throws Exception {
				up();
			}
		});
		down = new Image("/images/downarrow_g.jpg");
		down.addEventListener(Events.ON_CLICK, new EventListener<Event>() {
			public void onEvent(Event event) throws Exception {
				down();
			}
		});
		bottom = new Image("/images/downdownarrow_g.jpg");
		bottom.addEventListener(Events.ON_CLICK, new EventListener<Event>() {
			public void onEvent(Event event) throws Exception {
				bottom();
			}
		});
		top.setStyle("cursor: pointer;");
		up.setStyle("cursor: pointer;");
		down.setStyle("cursor: pointer;");
		bottom.setStyle("cursor: pointer;");
		top.setParent(v);
		up.setParent(v);
		down.setParent(v);
		bottom.setParent(v);
		return v;
	}

	public void top() {
		Set<Listitem> set = getChosenLb().getSelectedItems();

		if (set.size() > 1) {
			return;
		}

		int i = 0;
		for (Iterator<Listitem> it = set.iterator(); it.hasNext();) {
			Object obj = it.next().getValue();
			getChosenDataModel().remove(obj);
			getChosenDataModel().add(i++, obj);
		}
	}

	public void up() {
		if (getChosenLb().getItems().size() == 0
				|| getChosenLb().getSelectedItems().size() > 1) {
			return;
		}

		int index = getChosenLb().getSelectedIndex();
		if (index <= 0)
			return;
		Object obj = getChosenLb().getSelectedItem().getValue();
		getChosenDataModel().remove(obj);
		getChosenDataModel().add(--index, obj);
		getChosenLb().setSelectedIndex(index);
	}

	public void down() {
		if (getChosenLb().getItems().size() == 0
				|| getChosenLb().getSelectedItems().size() > 1) {
			return;
		}

		int index = getChosenLb().getSelectedIndex();
		if (index < 0 || index == getChosenDataModel().size() - 1)
			return;
		Object obj = getChosenLb().getSelectedItem().getValue();
		getChosenDataModel().remove(obj);
		getChosenDataModel().add(++index, obj);
		getChosenLb().setSelectedIndex(index);
	}

	public void bottom() {
		Set<Listitem> set = getChosenLb().getSelectedItems();

		if (set.size() > 1) {
			return;
		}

		for (Iterator<Listitem> it = set.iterator(); it.hasNext();) {
			Object obj = it.next().getValue();
			getChosenDataModel().remove(obj);
			getChosenDataModel().add(obj);
		}
	}

	public void setHeight(String height) {
		super.setHeight(height);
		getCandidateLb().setHeight(height);
		getChosenLb().setHeight(height);
	}

	public void setRenderer(String rendererClass) {
		try {
			DualListitemRenderer renderer = 
					(DualListitemRenderer) Class.forName(rendererClass).newInstance();
			setRenderer(renderer);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @param dataRenderer
	 */
	public void setRenderer(DualListitemRenderer dataRenderer) {
		setCandidateRenderer(dataRenderer);
		setChosenRenderer(dataRenderer);
	}

	public void setCandidateRenderer(DualListitemRenderer dataRenderer) {
		getCandidateLb().setItemRenderer(dataRenderer);
		Listhead listhead = dataRenderer.getListhead();
		if (listhead != null) {
			getCandidateLb().getChildren().add(listhead);
		}
		getCandidateLb().invalidate();
	}

	public void setChosenRenderer(DualListitemRenderer dataRenderer) {
		getChosenLb().setItemRenderer(dataRenderer);
		Listhead listhead = dataRenderer.getListhead();

		if (listhead != null) {
			getChosenLb().getChildren().add(dataRenderer.getListhead());
		}

		getChosenLb().invalidate();
	}

	/**
	 * set new candidate ListModelList.
	 * 
	 * @param unselectModel
	 */
	public void setModel(ListModelList<Object> unselectModel) {
		getCandidateLb().setModel(this.setCandidateModel(unselectModel));
		getChosenDataModel().clear();
	}

	/**
	 * @return get current chosen records.
	 */
	public List<Object> getChosenDataList() {
		return new ArrayList<Object>(getChosenDataModel());
	}

	public List<Listitem> getChosenItems() {
		return getChosenLb().getItems();
	}

	// Getter and Setter
	protected void setCandidateLb(Listbox candidateLb) {
		this.candidateLb = candidateLb;
	}

	protected Listbox getCandidateLb() {
		return candidateLb;
	}

	protected void setChosenLb(Listbox chosenLb) {
		this.chosenLb = chosenLb;
	}

	protected Listbox getChosenLb() {
		return chosenLb;
	}

	protected ListModelList<Object> setCandidateModel(
			ListModelList<Object> candidateModel) {
		this.candidateModel = candidateModel;
		this.candidateModel.setMultiple(true);
		return candidateModel;
	}

	protected ListModelList<Object> getCandidateModel() {
		return candidateModel;
	}

	protected ListModelList<Object> setChosenDataModel(
			ListModelList<Object> chosenDataModel) {
		this.chosenDataModel = chosenDataModel;
		this.chosenDataModel.setMultiple(true);
		return chosenDataModel;
	}

	protected ListModelList<Object> getChosenDataModel() {
		return chosenDataModel;
	}

}
