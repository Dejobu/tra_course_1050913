package composite.mvc;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.IdSpace;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zk.ui.select.Selectors;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Div;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listhead;
import org.zkoss.zul.Listitem;

import composite.DualListitemRenderer;

public class DualMVCListbox extends Div implements IdSpace {

	private static final long serialVersionUID = 4946109119959408605L;

	@Wire
	private Listbox candidateLb;
	@Wire
	private Listbox chosenLb;
	private ListModelList<Object> candidateModel;
	private ListModelList<Object> chosenDataModel;

	public DualMVCListbox() {
		Executions.createComponents("/dual-listbox/_duallistbox.zul", this, null);
		Selectors.wireComponents(this, this, false);
		Selectors.wireEventListeners(this, this);
		chosenLb.setModel(chosenDataModel = new ListModelList<Object>());
		chosenDataModel.setMultiple(true);
	}

	@Listen("onClick = #chooseAllBtn")
	public void clickChooseAllBtn() {
		chooseAll();
	}

	@Listen("onClick = #chooseBtn")
	public void clickChooseBtn() {
		Set<Listitem> set = chooseOne();
		Events.postEvent(new ChooseEvent(this, set));
	}

	@Listen("onClick = #removeBtn")
	public void clickRemoveBtn() {
		Set<Listitem> set = remove();
		Events.postEvent(new ChooseEvent(this, set));
	}

	@Listen("onClick = #removeAllBtn")
	public void clickRemoveAllBtn() {
		removeAll();
	}

	@Listen("onClick = #topBtn")
	public void clickTopBtn() {
		top();
	}

	@Listen("onClick = #upBtn")
	public void clickUpBtn() {
		up();
	}

	@Listen("onClick = #downBtn")
	public void clickDownBtn() {
		down();
	}

	@Listen("onClick = #bottomBtn")
	public void clickBottomBtn() {
		bottom();
	}

	public void chooseAll() {
		for (int i = 0, j = candidateModel.getSize(); i < j; i++) {
			chosenDataModel.add(candidateModel.getElementAt(i));
		}
		candidateModel.clear();
	}

	public Set<Listitem> chooseOne() {
		Set<Listitem> set = candidateLb.getSelectedItems();
		for (Listitem item : new ArrayList<Listitem>(set)) {
			Object data = item.getValue();
			chosenDataModel.add(data);
			candidateModel.remove(data);
		}
		return set;
	}

	public Set<Listitem> remove() {
		Set<Listitem> set = chosenLb.getSelectedItems();
		for (Listitem item : new ArrayList<Listitem>(set)) {
			Object data = item.getValue();
			candidateModel.add(data);
			chosenDataModel.remove(data);
		}
		return set;
	}

	public void removeAll() {
		for (int i = 0, j = chosenDataModel.getSize(); i < j; i++) {
			candidateModel.add(chosenDataModel.getElementAt(i));
		}
		chosenDataModel.clear();
	}

	public void top() {
		Set<Listitem> set = chosenLb.getSelectedItems();
		int i = 0;
		for (Object obj : new ArrayList<Listitem>(set)) {
			obj = ((Listitem) obj).getValue();
			chosenDataModel.remove(obj);
			chosenDataModel.add(i++, obj);
		}
	}

	public void up() {
		int index = chosenLb.getSelectedIndex();
		if (index == 0)
			return;
		Object obj = chosenLb.getSelectedItem().getValue();
		chosenDataModel.remove(obj);
		chosenDataModel.add(--index, obj);
		chosenLb.setSelectedIndex(index);
	}

	public void down() {
		int index = chosenLb.getSelectedIndex();
		if (index == chosenDataModel.size() - 1)
			return;
		Object obj = chosenLb.getSelectedItem().getValue();
		chosenDataModel.remove(obj);
		chosenDataModel.add(++index, obj);
		chosenLb.setSelectedIndex(index);
	}

	public void bottom() {
		Set<Listitem> set = chosenLb.getSelectedItems();
		for (Object obj : new ArrayList<Listitem>(set)) {
			obj = ((Listitem) obj).getValue();
			chosenDataModel.remove(obj);
			chosenDataModel.add(obj);
		}
	}

	// Set Renderer of candidate list and chosen list
	public void setRenderer(DualListitemRenderer dataRenderer) {
		setCandidateRenderer(dataRenderer);
		setChosenRenderer(dataRenderer);
	}

	// Set Renderer of candidate list and chosen list by string (Used in ZUML)
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
	 * Set renderer to candidate list
	 * 
	 * @param dataRenderer
	 *            is the renderer for candidate list
	 */
	public void setCandidateRenderer(DualListitemRenderer dataRenderer) {
		candidateLb.setItemRenderer(dataRenderer);
		Listhead listhead = dataRenderer.getListhead();
		if (listhead != null) {
			candidateLb.getChildren().add(listhead);
		}
		candidateLb.invalidate();
	}

	/**
	 * Set renderer to chosen list
	 * 
	 * @param dataRenderer
	 *            is the renderer for chosen list
	 */
	public void setChosenRenderer(DualListitemRenderer dataRenderer) {
		chosenLb.setItemRenderer(dataRenderer);
		Listhead listhead = dataRenderer.getListhead();
		if (listhead != null) {
			chosenLb.getChildren().add(dataRenderer.getListhead());
		}
		chosenLb.invalidate();
	}

	/**
	 * Set new candidate ListModelList.
	 * 
	 * @param candidate
	 *            is the data of candidate list model
	 */
	public void setModel(List<Object> candidate) {
		candidateLb.setModel(this.candidateModel = new ListModelList<Object>(candidate));
		candidateModel.setMultiple(true);
		chosenDataModel.clear();
	}

	/**
	 * @return current chosen data list
	 */
	public List<Object> getChosenDataList() {
		return new ArrayList<Object>(chosenDataModel);
	}

}
