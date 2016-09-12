package org.zkoss.training.mvvm;

import java.util.List;

import org.zkoss.bind.annotation.Init;
import org.zkoss.training.shop.model.ProductDAO;
import org.zkoss.training.shop.model.bean.Product;
import org.zkoss.zul.ListModel;
import org.zkoss.zul.ListModelArray;
import org.zkoss.zul.ListModelList;

public class ChildrenTemplateVM {

	private ProductDAO prodDAO = new ProductDAO();
	private List<Product> prodModel;
	private Mode mode = Mode.DEFAULT;
	public enum Mode {DEFAULT, SMALL, LIST};
	private String selectedMode = mode.name();
	private ListModelList<String> modeList;
	
	@Init
	public void init() {
		prodModel = prodDAO.findAllAvailable();
		modeList = new ListModelList<String>();
		for (Mode m : Mode.values()){
			modeList.add(m.name());
		}
	}

	public List<Product> getProdModel() {
		return prodModel;
	}

	public String getMode() {
		return mode.name();
	}

	public void setMode(Mode mode) {
		this.mode = mode;
	}
	
	public ListModel<String> getModeList(){
		return modeList;
	}

	public String getSelectedMode() {
		return selectedMode;
	}

	public void setSelectedMode(String selectedMode) {
		this.selectedMode = selectedMode;
	}
}
